import SwiftUI
import Shared

struct ContentView: View {
    @State private var viewModel: AnalyticsViewModel = AnalyticsViewModel(useCase: KoinHelper().getFullAnalyticsUseCase)

    var body: some View {
        Group {
            switch viewModel.state {
            case .idle: 
                SearchScreen(onSearch: viewModel.search)
            case .loading:
                LoadingScreen()
            case .error(let message):
                ErrorScreen(message: message, onRetry: viewModel.retry)
            case .success(let result):
                AnalyticsScreen(result: result, onBack: viewModel.reset)
            }
        }
        .background(Color(.systemGroupedBackground))
    }
}

#Preview {
    ContentView()
}
