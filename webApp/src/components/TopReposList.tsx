import type { Repository } from "#/lib/types/Repository"; // use your DTO type name

export function TopReposList({ repos }: { repos: Repository[] }) {
	if (repos.length === 0) return null;

	return (
		<div className="w-full rounded-2xl bg-surface p-5">
			<h3 className="text-base font-semibold text-on-surface">
				Top Repositories
			</h3>

			<div className="mt-3">
				{repos.map((repo, index) => (
					<div key={repo.id}>
						<RepoRow repo={repo} />
						{index < repos.length - 1 && (
							<hr className="my-2 border-on-surface/10" />
						)}
					</div>
				))}
			</div>
		</div>
	);
}

function RepoRow({ repo }: { repo: Repository }) {
	return (
		<div>
			<div className="flex justify-between gap-2">
				<span className="min-w-0 flex-1 text-sm font-medium text-primary">
					{repo.name}
				</span>
				<span className="text-[13px] text-on-surface/70">
					⭐ {repo.stargazers_count}
				</span>
			</div>

			{repo.description && (
				<p className="line-clamp-2 text-xs text-on-surface/50">
					{repo.description}
				</p>
			)}

			{repo.language && (
				<p className="text-[11px] text-secondary">{repo.language}</p>
			)}
		</div>
	);
}
