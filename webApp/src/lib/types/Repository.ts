export type Repository = {
	id: number;
	name: string;
	full_name: string;
	description: string | null;
	language: string | null;
	stargazers_count: number;
	forks_count: number;
	watchers_count: number;
	html_url: string;
	fork: boolean;
	updated_at: string;
};
