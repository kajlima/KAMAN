package dev.fathoor.kaman

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.fathoor.kaman.navigation.NavigationHost
import dev.fathoor.kaman.navigation.screen.Nav
import dev.fathoor.kaman.presentation.theme.KAMANTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KAMANTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NavigationHost(
                        navController = rememberNavController(),
                        startDestination = Nav.Splash.route
                    )
                }
            }
        }
    }
}
