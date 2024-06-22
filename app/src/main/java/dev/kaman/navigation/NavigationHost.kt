package dev.kaman.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.kaman.core.util.SetSystemUI
import dev.kaman.navigation.screen.Nav
import dev.kaman.presentation.auth.AuthScreen
import dev.kaman.presentation.home.HomeScreen
import dev.kaman.presentation.splash.AppSplashScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        // Splash
        composable(Nav.Splash.route) {
            SetSystemUI(
                statusColor = Color(0xFF1676F3),
                navigationColor = Color(0xFF1676F3),
                lightStatusBar = true,
                fullScreen = true
            )
            AppSplashScreen(
                navigateToHome = { id ->
                    navController.popBackStack()
                    navController.navigate(
                        Nav.Home.route.replace(
                            "{id}",
                            id.toString()
                        )
                    )
                },
                navigateToAuth = {
                    navController.navigate(
                        Nav.Auth.route.replace(
                            "{type}",
                            "login"
                        )
                    )
                }
            )
        }

        // Auth
        composable(
            route = Nav.Auth.route,
            arguments = listOf(
                navArgument("type") {
                    type = NavType.StringType
                }
            ),
        ) {
            val type = it.arguments?.getString("type") ?: "login"
            SetSystemUI(
                Color(0xFFF0F8FE),
                Color(0xFFF0F8FE),
                lightStatusBar = true,
                fullScreen = false
            )
            AuthScreen(
                type = type,
                navigateToMain = { id ->
                    navController.navigate(
                        Nav.Home.route.replace("{id}", id.toString())
                    ) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                navigateToLogin = {
                    navController.navigate(
                        Nav.Auth.route.replace("{type}", "login")
                    ) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                    }
                },
                navigateToRegister = {
                    navController.navigate(
                        Nav.Auth.route.replace("{type}", "register")
                    )
                }
            )
        }

        // Home
        composable(
            route = Nav.Home.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                }
            ),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
        ) {
            val session = it.arguments?.getLong("id") ?: 0
            SetSystemUI(
                Color(0xFFFFFFFF),
                Color(0xFFF0F8FE),
                lightStatusBar = true,
                fullScreen = false
            )
            HomeScreen(session = session, navController = navController)
        }
    }
}
