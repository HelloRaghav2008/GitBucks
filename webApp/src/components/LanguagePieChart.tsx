import { defineChart } from "@tanstack/charts";
import { pie, polar, radialArc } from "@tanstack/charts/polar";
import { Chart } from "@tanstack/charts/react";
import type { LanguageStat } from "#/lib/types/languageStat";

const LANGUAGE_COLORS = [
	"#6366F1",
	"#EC4899",
	"#14B8A6",
	"#F59E0B",
	"#10B981",
	"#3B82F6",
	"#F97316",
	"#8B5CF6",
];

export function LanguagePieChart({ stats }: { stats: LanguageStat[] }) {
	if (stats.length === 0) return null;

	const slices = pie(stats, { value: "bytes" });

	const languages = stats.map((row) => row.language);

	const definition = defineChart({
		marks: [
			polar({
				inset: 8,
				marks: [
					radialArc(slices, {
						color: "language",
						key: "language",
					}),
				],
				scales: {
					angle: null,
					radius: null,
				},
			}),
		],
		scales: {
			x: null,
			y: null,
		},
		color: {
			domain: languages,
			range: LANGUAGE_COLORS,
		},
	});

	return (
		<div className="w-full rounded-2xl bg-surface p-5">
			<h3 className="text-base font-semibold text-on-surface">
				Language Distribution
			</h3>
			<div className="mt-4 flex items-center gap-5">
				<div className="h-35 w-35 shrink-0">
					<Chart
						ariaLabel="Language distribution"
						definition={definition}
						height={140}
					/>
				</div>
				<ul className="flex flex-col gap-1.5">
					{stats.map((s, i) => (
						<li
							key={s.language}
							className="flex items-center gap-1.5 text-xs text-on-surface/85"
						>
							<span
								className="h-2.5 w-2.5 rounded-full"
								style={{
									backgroundColor: LANGUAGE_COLORS[i % LANGUAGE_COLORS.length],
								}}
							/>
							{s.language} {(Math.trunc(s.percentage * 10) / 10).toFixed(1)}%
						</li>
					))}
				</ul>
			</div>
		</div>
	);
}
