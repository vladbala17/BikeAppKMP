import androidx.compose.ui.window.ComposeUIViewController
import di.AppModule
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle
import platform.darwin.NSObject

fun MainViewController() = ComposeUIViewController {
    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark
    App(
        darkTheme = isDarkTheme,
        dynamicColor = false,
        AppModule(NSObject())
    )
}