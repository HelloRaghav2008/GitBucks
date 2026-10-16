import { barY, defineChart } from "@tanstack/charts";
import { Chart } from "@tanstack/charts/react"; // check: some examples use '@tanstack/charts/react/core'
import { scaleBand } from "@tanstack/charts/scales/band";
import { scaleLinear } from "@tanstack/charts/scales/linear";
import { useMemo } from "react";
import type { CommitActivity } from "#/lib/types/commitActivity";

export function CommitTrendChart({ trend }: { trend: CommitActivity[] }) {
	const definition = useMemo(() => {
		const maxCount = Math.max(...trend.map((t) => t.count), 1);
		return defineChart({
			marks: [
				barY(trend, {
					x: "month",
					y: "count",
					fill: "#6366F1",
				}),
			],
			guides: false,
			scales: {
				x: {
					scale: () => scaleBand<string>().paddingInner(0.35).paddingOuter(0.175),
					axis: false,
				},
				y: { 
					scale: scaleLinear().domain([0, maxCount]), 
					axis: false 
				},
			},
		});
	}, [trend]);

	if (trend.length === 0) return null;

	return (
		<div className="w-full rounded-2xl bg-surface p-5">
			<h3 className="text-base font-semibold text-on-surface">
				Commit Trend (last 12 months)
			</h3>
			<p className="text-[11px] text-on-surface/50">
				Aggregated across top 5 repos
			</p>

			<div className="mt-4 h-30 w-full">
				<Chart ariaLabel="Commit trend" definition={definition} height={120} />
			</div>

			<p className="mt-2 text-[11px] text-on-surface/45">
				{trend[0].month} → {trend[trend.length - 1].month}
			</p>
		</div>
	);
}
