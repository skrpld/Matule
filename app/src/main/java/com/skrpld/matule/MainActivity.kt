package com.skrpld.matule

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.skrpld.matule.data.repositories.AuthManager
import com.skrpld.matule.ui.screens.auth.AuthViewModel
import com.skrpld.matule.ui.screens.auth.LockScreen
import com.skrpld.matule.ui.screens.auth.LockScreenMode
import com.skrpld.matule.ui.theme.MatuleTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatuleTheme {
                LockScreen(AuthViewModel(AuthManager()), mode = LockScreenMode.Signup)
            }
        }
    }
}