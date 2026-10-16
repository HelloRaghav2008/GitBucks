enum AnalyticsState {
    case idle
    case loading
    case success(AnalyticsResults)
    case error(String)
}
