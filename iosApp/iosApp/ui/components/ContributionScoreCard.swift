import SwiftUI
import Shared

struct ContributionScoreCard: View {
    let score: Double

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text("Contribution Score")
                .font(.system(size: 13))
                .foregroundStyle(.primary.opacity(0.6))

            Text(String(Int64(score)))
                .font(.system(size: 36, weight: .bold))
                .foregroundStyle(Color.accentColor)
                .padding(.top, 4)

            Text("stars×3 + forks×2 + watchers×1 (original repos only)")
                .font(.system(size: 11))
                .foregroundStyle(.primary.opacity(0.45))
                .padding(.top, 4)
        }
        .padding(20)
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(Color(.secondarySystemGroupedBackground),
                    in: RoundedRectangle(cornerRadius: 16))
    }
}