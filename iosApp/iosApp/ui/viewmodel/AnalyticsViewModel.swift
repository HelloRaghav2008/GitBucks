import Foundation
import Shared

@MainActor
@Observable
final class AnalyticsViewModel {
    private(set) var uiState: AnalyticsState = .idle

    private let analyticsUseCase: GetFullAnalyticsUseCase
    private var task: Task<Void, Never>?

    init(analyticsUseCase: GetFullAnalyticsUseCase) {
        self.analyticsUseCase = analyticsUseCase
    }

    func analyze(userName: String) {
        guard !userName.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty else { return }

        task?.cancel()
        task = Task {
            uiState = .loading
            do {
                let result = try await analyticsUseCase.execute(userName: userName)
                uiState = .success(result)
            } catch is CancellationError {
                return
            } catch {
                uiState = .error(error.localizedDescription)
            }
        }
    }
    

    func reset() {
        task?.cancel()
        uiState = .idle
    }

}
