package com.skrpld.matule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.skrpld.matule.ui.navigation.AppNavHost
import com.skrpld.matule.ui.screens.splash.SplashScreen
import com.skrpld.matule.ui.theme.MatuleTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var showSplashScreen by remember { mutableStateOf(true) }

            LaunchedEffect(Unit) {
                delay(2000)
                showSplashScreen = false
            }

            MatuleTheme {
                if (showSplashScreen) {
                    SplashScreen()
                } else {
                    AppNavHost()
                }
            }
        }
    }
}