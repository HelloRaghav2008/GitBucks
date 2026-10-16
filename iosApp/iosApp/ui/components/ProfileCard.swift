import SwiftUI
import Shared

struct ProfileCard: View {
    let user: GitHubUser

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text(user.name ?? user.login)
                .font(.system(size: 22, weight: .bold))

            Text("@\(user.login)")
                .font(.system(size: 14))
                .foregroundStyle(Color.accentColor)

            Text(user.bio)
                .font(.system(size: 13))
                .foregroundStyle(.primary.opacity(0.7))
                .padding(.top, 8)

            HStack(spacing: 24) {
                StatChip(label: "Followers", value: "\(user.followers)")
                StatChip(label: "Following", value: "\(user.following)")
                StatChip(label: "Repos", value: "\(user.publicRepos)")
            }
            .padding(.top, 16)
        }
        .padding(20)
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(Color(.secondarySystemGroupedBackground),
                    in: RoundedRectangle(cornerRadius: 16))
    }
}

private struct StatChip: View {
    let label: String
    let value: String

    var body: some View {
        VStack(alignment: .center, spacing: 0) {
            Text(value)
                .font(.system(size: 18, weight: .bold))
                .foregroundStyle(Color.accentColor)
            Text(label)
                .font(.system(size: 11))
                .foregroundStyle(.primary.opacity(0.6))
        }
    }
}
