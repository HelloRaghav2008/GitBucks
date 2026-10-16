import SwiftUI

struct SearchScreen: View {
    let onSearch: (String) -> Void

    @State private var username = ""

    private var trimmed: String {
        username.trimmingCharacters(in: .whitespacesAndNewlines)
    }

    var body: some View {
        VStack(spacing: 0) {
            Text("GitHub Analytics")
                .font(.system(size: 28, weight: .bold))
                .foregroundStyle(Color.accentColor)

            Text("Powered by Kotlin Multiplatform")
                .font(.system(size: 13))
                .foregroundStyle(.primary.opacity(0.6))
                .multilineTextAlignment(.center)

            TextField("GitHub username", text: $username, prompt: Text("e.g. torvalds"))
                .textInputAutocapitalization(.never)
                .autocorrectionDisabled()
                .submitLabel(.go)
                .onSubmit(search)
                .padding(16)
                .glassEffect(.regular.interactive(), in: RoundedRectangle(cornerRadius: 12))
                .padding(.top, 48)

            Button(action: search) {
                Text("Analyze")
                    .font(.system(size: 16, weight: .semibold))
                    .frame(maxWidth: .infinity, minHeight: 52)
            }
            .buttonStyle(.glassProminent)
            .buttonBorderShape(.roundedRectangle(radius: 12))
            .disabled(trimmed.isEmpty)
            .padding(.top, 16)
        }
        .padding(32)
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }

    private func search() {
        guard !trimmed.isEmpty else { return }
        onSearch(trimmed)
    }
}