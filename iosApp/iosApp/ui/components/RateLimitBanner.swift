import SwiftUI
import Shared

struct RateLimitBanner: View {
    let remaining: Int32
    let total: Int32

    private var color: Color {
        if remaining > 30 { return Color(red: 0x10 / 255, green: 0xB9 / 255, blue: 0x81 / 255) }
        if remaining > 10 { return Color(red: 0xF5 / 255, green: 0x9E / 255, blue: 0x0B / 255) }
        return Color(red: 0xEF / 255, green: 0x44 / 255, blue: 0x44 / 255)
    }

    var body: some View {
        HStack(spacing: 4) {
            Text("GitHub API:")
                .font(.system(size: 11))
                .foregroundStyle(.primary.opacity(0.6))

            Text("\(remaining)/\(total) requests remaining")
                .font(.system(size: 11))
                .foregroundStyle(color)
        }
        .padding(.horizontal, 12)
        .padding(.vertical, 6)
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(color.opacity(0.12), in: RoundedRectangle(cornerRadius: 8))
    }
}
