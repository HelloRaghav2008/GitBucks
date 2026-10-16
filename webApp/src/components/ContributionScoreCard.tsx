export function ContributionScoreCard({ score }: { score: number }) {
	return (
		<div className="w-full rounded-2xl bg-surface p-5">
			<p className="text-[13px] text-on-surface/60">Contribution Score</p>
			<p className="mt-1 text-[36px] font-bold leading-tight text-primary">
				{Math.trunc(score)}
			</p>
			<p className="mt-1 text-[11px] text-on-surface/45">
				stars×3 + forks×2 + watchers×1 (original repos only)
			</p>
		</div>
	);
}
