package dev.fathoor.kaman.navigation.screen

sealed class Nav(
    val route: String
) {
    data object Splash : Nav(
        route = "splash"
    )

    data object Auth : Nav(
        route = "auth/{type}"
    )

    data object Home : Nav(
        route = "home/{id}"
    )

    data object Profile : Nav(
        route = "profile/{id}"
    )
}
