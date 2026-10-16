import { createFileRoute } from "@tanstack/react-router";
import { useState } from "react";
import type { AnalyticsResult } from "#/lib/types/analyticsResult";
import { AnalyticsScreen } from "#/screens/AnalyticsScreen";
import { ErrorScreen } from "#/screens/ErrorScreen";
import { LoadingScreen } from "#/screens/LoadingScreen";
import { SearchScreen } from "#/screens/SearchScreen";
import { analyseUser } from "#/server/analytics";

type AnalyticsState =
	| { status: "idle" }
	| { status: "loading" }
	| { status: "success"; analyticsResults: AnalyticsResult }
	| { status: "error"; message: string };

export const Route = createFileRoute("/")({ component: Home });

function Home() {
	const [state, setState] = useState<AnalyticsState>({ status: "idle" });

	async function analyze(userName: string) {
		setState({ status: "loading" });
		try {
			const result = await analyseUser({ data: userName });
			setState({ status: "success", analyticsResults: result });
		} catch (e) {
			const message =
				e instanceof Error ? e.message : "Failed to fetch analytics";
			setState({ status: "error", message });
		}
	}

	const reset = () => setState({ status: "idle" });

	switch (state.status) {
		case "idle":
			return <SearchScreen onSearch={analyze} />;
		case "loading":
			return <LoadingScreen />;
		case "success":
			return <AnalyticsScreen result={state.analyticsResults} onBack={reset} />;
		case "error":
			return <ErrorScreen message={state.message} onRetry={reset} />;
	}
}
