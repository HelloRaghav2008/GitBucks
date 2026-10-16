import SwiftUI
import Shared

struct TopReposList: View {
    let repos: [Repository]

    var body: some View {
        if !repos.isEmpty {
            VStack(alignment: .leading, spacing: 0) {
                Text("Top Repositories")
                    .font(.system(size: 16, weight: .semibold))

                VStack(alignment: .leading, spacing: 0) {
                    ForEach(Array(repos.enumerated()), id: \.element.id) { index, repo in
                        RepoRow(repo: repo)
                        if index < repos.count - 1 {
                            Rectangle()
                                .fill(.primary.opacity(0.1))
                                .frame(height: 1)
                                .padding(.vertical, 8)
                        }
                    }
                }
                .padding(.top, 12)
            }
            .padding(20)
            .frame(maxWidth: .infinity, alignment: .leading)
            .background(Color(.secondarySystemGroupedBackground),
                        in: RoundedRectangle(cornerRadius: 16))
        }
    }
}

private struct RepoRow: View {
    let repo: Repository

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            HStack {
                Text(repo.name)
                    .font(.system(size: 14, weight: .medium))
                    .foregroundStyle(Color.accentColor)
                    .frame(maxWidth: .infinity, alignment: .leading)

                Text("⭐ \(repo.stars)")
                    .font(.system(size: 13))
                    .foregroundStyle(.primary.opacity(0.7))
            }

            if let description = repo.description_ {
                Text(description)
                    .font(.system(size: 12))
                    .foregroundStyle(.primary.opacity(0.5))
                    .lineLimit(2)
            }

            if let language = repo.language {
                Text(language)
                    .font(.system(size: 11))
                    .foregroundStyle(.secondary)
            }
        }
    }
}
