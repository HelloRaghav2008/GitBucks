import SwiftUI
import Shared

struct CommitTrendChart: View {
    let trend: [CommitActivity]

    private let barColor = Color(red: 0x63 / 255, green: 0x66 / 255, blue: 0xF1 / 255)

    private var maxCount: Int {
        max(trend.map { Int($0.count) }.max() ?? 1, 1)
    }

    var body: some View {
        if !trend.isEmpty {
            VStack(alignment: .leading, spacing: 0) {
                Text("Commit Trend (last 12 months)")
                    .font(.system(size: 16, weight: .semibold))

                Text("Aggregated across top 5 repos")
                    .font(.system(size: 11))
                    .foregroundStyle(.primary.opacity(0.5))

                Canvas { context, size in
                    let spacing = size.width / CGFloat(trend.count)
                    let barWidth = spacing * 0.65

                    for (i, activity) in trend.enumerated() {
                        let barHeight = CGFloat(activity.count) / CGFloat(maxCount) * size.height
                        let x = CGFloat(i) * spacing + (spacing - barWidth) / 2
                        let y = size.height - barHeight

                        context.fill(
                            Path(CGRect(x: x, y: y, width: barWidth, height: barHeight)),
                            with: .color(barColor)
                        )
                    }
                }
                .frame(height: 120)
                .padding(.top, 16)

                Text("\(trend.first!.month) → \(trend.last!.month)")
                    .font(.system(size: 11))
                    .foregroundStyle(.primary.opacity(0.45))
                    .padding(.top, 8)
            }
            .padding(20)
            .frame(maxWidth: .infinity, alignment: .leading)
            .background(Color(.secondarySystemGroupedBackground),
                        in: RoundedRectangle(cornerRadius: 16))
        }
    }
}
