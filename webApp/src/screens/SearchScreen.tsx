import { useState } from "react";

export function SearchScreen({
	onSearch,
}: {
	onSearch: (userName: string) => void;
}) {
	const [username, setUsername] = useState("");
	const trimmed = username.trim();

	return (
		<div className="flex min-h-screen flex-col items-center justify-center p-8">
			<div className="flex w-full max-w-md flex-col items-center">
				<h1 className="text-[28px] font-bold text-primary">GitHub Analytics</h1>
				<p className="text-center text-[13px] text-on-surface/60">
					Powered by Kotlin Multiplatform
				</p>

				<form
					className="mt-12 w-full"
					onSubmit={(e) => {
						e.preventDefault();
						if (trimmed) onSearch(trimmed);
					}}
				>
					<label className="relative block w-full">
						<span className="absolute -top-2 left-3 bg-surface px-1 text-xs text-on-surface/60">
							GitHub username
						</span>
						<input
							value={username}
							onChange={(e) => setUsername(e.target.value)}
							placeholder="e.g. torvalds"
							autoCapitalize="none"
							autoCorrect="off"
							className="w-full rounded-xl border border-on-surface/40 bg-transparent px-4 py-4 outline-none focus:border-2 focus:border-primary"
						/>
					</label>

					<button
						type="submit"
						disabled={!trimmed}
						className="mt-4 h-13 w-full rounded-xl bg-primary text-base font-semibold text-on-primary disabled:bg-on-surface/12 disabled:text-on-surface/40"
					>
						Analyze
					</button>
				</form>
			</div>
		</div>
	);
}
