export function ErrorScreen({
	message,
	onRetry,
}: {
	message: string;
	onRetry: () => void;
}) {
	return (
		<div className="flex min-h-screen flex-col items-center justify-center p-8">
			<h2 className="text-lg font-bold text-error">Something went wrong</h2>
			<p className="mt-2 text-center text-[13px] text-on-surface/60">
				{message}
			</p>
			<button
				type="button"
				onClick={onRetry}
				className="mt-6 h-10 rounded-full bg-primary px-6 text-sm font-medium text-on-primary"
			>
				Try Again
			</button>
		</div>
	);
}
