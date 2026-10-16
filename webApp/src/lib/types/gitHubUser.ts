export type GitHubUser = {
	login: string;
	name: string | null;
	avatar_url: string;
	bio: string;
	followers: number;
	following: number;
	public_repos: number;
	location: string | null;
	company: string | null;
	html_url: string;
};
