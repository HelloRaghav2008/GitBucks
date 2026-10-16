import type { CommitActivity } from "./commitActivity";
import type { GitHubUser } from "./gitHubUser";
import type { LanguageStat } from "./languageStat";
import type { Repository } from "./Repository";

export type AnalyticsResult = {
	user: GitHubUser;
	topRepositories: Array<Repository>;
	languageStat: Array<LanguageStat>;
	contributionScore: number;
	commitTrend: Array<CommitActivity>;
	rateLimitRemaining: number;
	rateLimitTotal: number;
};
