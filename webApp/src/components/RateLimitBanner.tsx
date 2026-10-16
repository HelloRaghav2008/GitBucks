type Props = { remaining: number; total: number };

export function RateLimitBanner({ remaining, total }: Props) {
	// Full class names are written out so Tailwind can find them at build time.
	const colors =
		remaining > 30
			? { bg: "bg-[#10B981]/12", text: "text-[#10B981]" }
			: remaining > 10
				? { bg: "bg-[#F59E0B]/12", text: "text-[#F59E0B]" }
				: { bg: "bg-[#EF4444]/12", text: "text-[#EF4444]" };

	return (
		<div
			className={`flex w-full gap-1 rounded-lg px-3 py-1.5 text-[11px] ${colors.bg}`}
		>
			<span className="text-on-surface/60">GitHub API:</span>
			<span className={colors.text}>
				{remaining}/{total} requests remaining
			</span>
		</div>
	);
}