package dev.fathoor.kaman.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.fathoor.core.util.UIState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    session: Long,
    navController: NavHostController
) {
    val userSession by viewModel.session.collectAsState(0)
    val stateHome by viewModel.stateHome.collectAsState(UIState.Loading)
    val stateLogout by viewModel.stateLogout.collectAsState(UIState.Empty)

    LaunchedEffect(stateHome) {
        viewModel.getAllReport()
    }

    HomeContent(
        session = if (session.toInt() == 0) userSession else session,
        stateHome = stateHome,
        stateLogout = stateLogout,
        onRefresh = {
            viewModel.getAllReport()
        },
        onLogout = {
            viewModel.logout(if (session.toInt() == 0) userSession else session)
        },
        onSubmit = { report ->
            viewModel.insertReport(report)
        },
        navController = navController
    )
}
