import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        ApplicationModuleKt.doInitKoin()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
