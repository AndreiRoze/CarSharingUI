package com.andreirozov.carsharingui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andreirozov.carsharingui.ui.car.CarScreen
import com.andreirozov.carsharingui.ui.home.HomeScreen
import com.andreirozov.carsharingui.ui.theme.CarSharingUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CarSharingUIApp()
        }
    }
}

@Composable
fun CarSharingUIApp() {
    // navHostController
    val navHostController = rememberNavController()

    CarSharingUITheme {
        NavHost(
            navController = navHostController,
            startDestination = "home"
        ) {
            composable("home") {
                HomeScreen(navHostController = navHostController)
            }

            composable("car") {
                CarScreen()
            }
        }
    }
}