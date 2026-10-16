import SwiftUI
import Shared

private extension Color {
    init(hex: UInt32) {
        self.init(
            red: Double((hex >> 16) & 0xFF) / 255,
            green: Double((hex >> 8) & 0xFF) / 255,
            blue: Double(hex & 0xFF) / 255
        )
    }
}

private let languageColors: [Color] = [
    Color(hex: 0x6366F1), Color(hex: 0xEC4899), Color(hex: 0x14B8A6),
    Color(hex: 0xF59E0B), Color(hex: 0x10B981), Color(hex: 0x3B82F6),
    Color(hex: 0xF97316), Color(hex: 0x8B5CF6)
]

struct LanguagePieChart: View {
    let stats: [LanguageStat]

    private func color(at index: Int) -> Color {
        languageColors[index % languageColors.count]
    }

    private func percentText(_ value: Double) -> String {
        // Truncates to one decimal, same as (p * 10).toLong() / 10.0
        String(format: "%.1f", Double(Int64(value * 10)) / 10.0)
    }

    var body: some View {
        if !stats.isEmpty {
            VStack(alignment: .leading, spacing: 0) {
                Text("Language Distribution")
                    .font(.system(size: 16, weight: .semibold))

                HStack(alignment: .center, spacing: 20) {
                    Canvas { context, size in
                        let center = CGPoint(x: size.width / 2, y: size.height / 2)
                        let radius = min(size.width, size.height) / 2
                        var start = -90.0

                        for (i, stat) in stats.enumerated() {
                            let sweep = stat.percentage / 100.0 * 360.0
                            var path = Path()
                            path.move(to: center)
                            path.addArc(center: center,
                                        radius: radius,
                                        startAngle: .degrees(start),
                                        endAngle: .degrees(start + sweep),
                                        clockwise: false)
                            path.closeSubpath()
                            context.fill(path, with: .color(color(at: i)))
                            start += sweep
                        }
                    }
                    .frame(width: 140, height: 140)

                    VStack(alignment: .leading, spacing: 6) {
                        ForEach(Array(stats.enumerated()), id: \.offset) { i, stat in
                            HStack(spacing: 6) {
                                Circle()
                                    .fill(color(at: i))
                                    .frame(width: 10, height: 10)
                                Text("\(stat.language) \(percentText(stat.percentage))%")
                                    .font(.system(size: 12))
                                    .foregroundStyle(.primary.opacity(0.85))
                            }
                        }
                    }
                }
                .padding(.top, 16)
            }
            .padding(20)
            .frame(maxWidth: .infinity, alignment: .leading)
            .background(Color(.secondarySystemGroupedBackground),
                        in: RoundedRectangle(cornerRadius: 16))
        }
    }
}
