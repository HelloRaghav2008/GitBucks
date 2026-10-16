import { createServerFn } from "@tanstack/react-start";
import type { AnalyticsResult } from "#/lib/types/analyticsResult";
import type { CommitActivityRaw } from "#/lib/types/commitActivityRaw";
import type { GitHubUser } from "#/lib/types/gitHubUser";
import type { Repository } from "#/lib/types/Repository";
import {
	calculateLanguageStats,
	contributionScore,
	fetchCommitTrend,
} from "./calculations";

const BASE_URL = "https://api.github.com/";

export class GitHubApiError extends Error {
	status: number;
	constructor(message: string, status: number) {
		super(message);
		this.status = status;
	}
}

async function gitHubFetch(path: string): Promise<Response> {
	const headers: Record<string, string> = {
		Accept: "application/vnd.github+json",
	};

	const token = process.env.GITHUB_TOKEN;

	if (token) headers.Authorization = `Bearer ${token}`;

	const res = await fetch(`${BASE_URL}${path}`, { headers });

	if (res.status >= 400) {
		let message = `GitHub API error ${res.status}`;
		if ([401, 403, 429].includes(res.status)) {
			const body = (await res.json().catch(() => null)) as {
				message?: string;
			} | null;
			message = body?.message ?? message;
		}

		throw new GitHubApiError(message, res.status);
	}

	return res;
}

export async function getUser(username: string) {
	const res = await gitHubFetch(`/users/${encodeURIComponent(username)}`);
	const user = (await res.json()) as GitHubUser;
	const remaining = Number(res.headers.get("x-ratelimit-remaining") ?? 60);
	const limit = Number(res.headers.get("x-ratelimit-limit") ?? 60);
	return { user, remaining, limit };
}

export async function getRepos(username: string) {
	const res = await gitHubFetch(
		`/users/${encodeURIComponent(username)}/repos?per_page=100&sort=updated&type=owner`,
	);

	return (await res.json()) as Repository[];
}

export async function getLanguages(owner: string, repo: string) {
	const res = await gitHubFetch(`/repos/${owner}/${repo}/languages`);
	return (await res.json()) as Record<string, number>;
}

export async function getCommitActivity(owner: string, repo: string) {
	const res = await gitHubFetch(
		`/repos/${owner}/${repo}/stats/commit_activity`,
	);
	if (res.status === 202 || res.status === 204) return []; // GitHub still computing
	return (await res.json()) as CommitActivityRaw[];
}

async function getFullAnalytics(userName: string): Promise<AnalyticsResult> {
	const [{ user, remaining, limit }, repos] = await Promise.all([
		getUser(userName),
		getRepos(userName),
	]);

	const [languageStat, commitTrend] = await Promise.all([
		calculateLanguageStats(userName, repos),
		fetchCommitTrend(userName, repos),
	]);

	const topRepositories = repos
		.sort((a, b) => b.stargazers_count - a.stargazers_count)
		.slice(0, 10);

	return {
		user,
		topRepositories,
		languageStat,
		contributionScore: contributionScore(repos),
		commitTrend,
		rateLimitRemaining: remaining,
		rateLimitTotal: limit,
	};
}

export const analyseUser = createServerFn({ method: "POST" })
	.validator((userName: string) => userName.trim())
	.handler(async ({ data }) => {
		if (!data) throw new Error("Username is required");
		return getFullAnalytics(data);
	});
