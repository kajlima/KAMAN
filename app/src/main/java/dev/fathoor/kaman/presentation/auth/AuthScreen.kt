package dev.fathoor.kaman.presentation.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import dev.fathoor.core.util.UIState

@Composable
fun AuthScreen(
    type: String,
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToMain: (session: Long) -> Unit,
    navigateToLogin: () -> Unit,
    navigateToRegister: () -> Unit
) {
    val stateLogin by viewModel.stateLogin.collectAsState(UIState.Empty)
    val stateRegister by viewModel.stateRegister.collectAsState(UIState.Empty)
    val session by viewModel.session

    when (type) {
        "login" -> {
            LoginContent(
                stateLogin = stateLogin,
                session = session,
                navigateToMain = navigateToMain,
                navigateToRegister = navigateToRegister,
                onSubmit = { user ->
                    viewModel.login(user)
                }
            )
        }

        "register" -> {
            RegisterContent(
                stateRegister = stateRegister,
                session = session,
                navigateToMain = navigateToMain,
                navigateToLogin = navigateToLogin,
                onSubmit = { user ->
                    viewModel.register(user)
                }
            )
        }
    }
}
