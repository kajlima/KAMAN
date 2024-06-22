package dev.kaman.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.kaman.R
import kotlinx.coroutines.delay

@Composable
fun AppSplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navigateToHome: (Long) -> Unit,
    navigateToAuth: () -> Unit
) {
    val session by viewModel.session.collectAsState(0)
    val isLoading by viewModel.isLoading.collectAsState(true)

    LaunchedEffect(isLoading) {
        delay(2000L)

        if (session.toInt() != 0) {
            navigateToHome(session)
        } else {
            navigateToAuth()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1676F3)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(140.dp)
                .background(Color(0xFFFFFFFF), CircleShape)
        )
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null
        )
    }
}
