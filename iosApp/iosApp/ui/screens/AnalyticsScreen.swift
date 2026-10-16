import SwiftUI
import Shared

struct AnalyticsScreen: View {
    let result: AnalyticsResults
    let onBack: () -> Void

    var body: some View {
        ScrollView {
            VStack(spacing: 12) {
                RateLimitBanner(remaining: result.rateLimitRemaining,
                                total: result.rateLimitTotal)

                ProfileCard(user: result.user)

                ContributionScoreCard(score: result.contributionScore)

                LanguagePieChart(stats: result.languageStat)

                CommitTrendChart(trend: result.commitTrend)

                TopReposList(repos: result.topRepositories)

                Color.clear.frame(height: 8)

                Button(action: onBack) {
                    Text("Search Another User")
                        .frame(maxWidth: .infinity)
                }
                .controlSize(.large)
                .buttonStyle(.glassProminent)

                Color.clear.frame(height: 24)
            }
            .padding(16)
        }
    }
}

struct LoadingScreen: View {
    var body: some View {
        VStack(spacing: 16) {
            ProgressView()
                .controlSize(.large)
                .tint(Color.accentColor)
            Text("Crunching GitHub data...")
                .foregroundStyle(.primary.opacity(0.7))
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}

struct ErrorScreen: View {
    let message: String
    let onRetry: () -> Void

    var body: some View {
        VStack(spacing: 0) {
            Text("Something went wrong")
                .font(.system(size: 18, weight: .bold))
                .foregroundStyle(Color.red)

            Text(message)
                .font(.system(size: 13))
                .foregroundStyle(.primary.opacity(0.6))
                .padding(.top, 8)

            Button("Try Again", action: onRetry)
                .buttonStyle(.glassProminent)
                .padding(.top, 24)
        }
        .padding(32)
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}