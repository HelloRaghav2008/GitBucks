export function LoadingScreen() {
	return (
		<div className="flex min-h-screen flex-col items-center justify-center">
			<div className="h-10 w-10 animate-spin rounded-full border-4 border-primary border-t-transparent" />
			<p className="mt-4 text-on-surface/70">Crunching GitHub data...</p>
		</div>
	);
}
