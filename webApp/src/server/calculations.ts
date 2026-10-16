import type { CommitActivity } from "#/lib/types/commitActivity";
import type { LanguageStat } from "#/lib/types/languageStat";
import type { Repository } from "#/lib/types/Repository";
import { getCommitActivity, getLanguages } from "./analytics";

export function contributionScore(repos: Repository[]): number {
	return repos
		.filter((repo) => !repo.fork)
		.reduce(
			(sum, repo) =>
				sum +
				repo.stargazers_count * 3 +
				repo.forks_count * 2 +
				repo.watchers_count,
			0,
		);
}

export async function calculateLanguageStats(
	username: string,
	repos: Repository[],
): Promise<LanguageStat[]> {
	const ownRepos = repos.filter((repo) => !repo.fork).slice(0, 30);

	const languageMaps = await Promise.all(
		ownRepos.map((repo) =>
			getLanguages(username, repo.name).catch(
				() => ({}) as Record<string, number>,
			),
		),
	);

	const merged: Record<string, number> = {};
	for (const map of languageMaps) {
		for (const [lang, bytes] of Object.entries(map)) {
			merged[lang] = (merged[lang] ?? 0) + bytes;
		}
	}

	const entries = Object.entries(merged);
	const total = entries.reduce((sum, [, bytes]) => sum + bytes, 0);
	if (total === 0) return [];

	return entries
		.sort((a, b) => b[1] - a[1])
		.slice(0, 8)
		.map(([language, bytes]) => ({
			language,
			bytes,
			percentage: (bytes / total) * 100,
		}));
}

function formatEpochToYearMonth(epochSeconds: number): string {
	const date = new Date(epochSeconds * 1000);
	const year = date.getUTCFullYear();
	const month = String(date.getUTCMonth() + 1).padStart(2, "0");
	return `${year}-${month}`;
}

export async function fetchCommitTrend(
	username: string,
	repos: Repository[],
): Promise<CommitActivity[]> {
	const top5 = repos
		.filter((repo) => !repo.fork)
		.sort((a, b) => b.stargazers_count - a.stargazers_count)
		.slice(0, 5);

	// Parallel fetch. A failing repo counts as "no data".
	const allWeeklyData = (
		await Promise.all(
			top5.map((repo) =>
				getCommitActivity(username, repo.name).catch(() => []),
			),
		)
	).flat();

	// Aggregate by month across all repos
	const monthlyTotals: Record<string, number> = {};
	for (const weekly of allWeeklyData) {
		if (weekly.total > 0) {
			const monthKey = formatEpochToYearMonth(weekly.week);
			monthlyTotals[monthKey] = (monthlyTotals[monthKey] ?? 0) + weekly.total;
		}
	}

	// Last 12 months, sorted chronologically
	return Object.entries(monthlyTotals)
		.sort(([a], [b]) => a.localeCompare(b))
		.slice(-12)
		.map(([month, count]) => ({ month, count }));
}
