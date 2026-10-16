import { CommitTrendChart } from "#/components/CommitTrendChart.tsx";
import { ContributionScoreCard } from "#/components/ContributionScoreCard.tsx";
import { LanguagePieChart } from "#/components/LanguagePieChart.tsx";
import { ProfileCard } from "#/components/ProfileCard.tsx";
import { RateLimitBanner } from "#/components/RateLimitBanner.tsx";
import { TopReposList } from "#/components/TopReposList.tsx";
import type { AnalyticsResult } from "#/lib/types/analyticsResult";

export function AnalyticsScreen({
	result,
	onBack,
}: {
	result: AnalyticsResult;
	onBack: () => void;
}) {
	return (
		<div className="mx-auto flex max-w-2xl flex-col gap-3 p-4 pb-12">
			<RateLimitBanner
				remaining={result.rateLimitRemaining}
				total={result.rateLimitTotal}
			/>
			<ProfileCard user={result.user} />
			<ContributionScoreCard score={result.contributionScore} />
			<LanguagePieChart stats={result.languageStat} />
			<CommitTrendChart trend={result.commitTrend} />
			<TopReposList repos={result.topRepositories} />

			<button
				type="button"
				onClick={onBack}
				className="mt-2 h-10 w-full rounded-full bg-primary text-sm font-medium text-on-primary"
			>
				Search Another User
			</button>
		</div>
	);
}
