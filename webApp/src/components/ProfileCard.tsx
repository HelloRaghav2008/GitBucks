import type { GitHubUser } from "#/lib/types/gitHubUser";

export function ProfileCard({ user }: { user: GitHubUser }) {
	return (
		<div className="w-full rounded-2xl bg-surface p-5">
			<h2 className="text-[22px] font-bold text-on-surface">
				{user.name ?? user.login}
			</h2>
			<p className="text-sm text-primary">@{user.login}</p>
			<p className="mt-2 text-[13px] text-on-surface/70">{user.bio ?? ""}</p>

			<div className="mt-4 flex gap-6">
				<StatChip label="Followers" value={user.followers} />
				<StatChip label="Following" value={user.following} />
				<StatChip label="Repos" value={user.public_repos} />
			</div>
		</div>
	);
}

function StatChip({ label, value }: { label: string; value: number }) {
	return (
		<div className="flex flex-col items-center">
			<span className="text-lg font-bold text-primary">{value}</span>
			<span className="text-[11px] text-on-surface/60">{label}</span>
		</div>
	);
}
