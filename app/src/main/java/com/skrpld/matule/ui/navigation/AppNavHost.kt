package com.skrpld.matule.ui.navigation

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.skrpld.matule.data.models.AuthState
import com.skrpld.matule.ui.screens.auth.*
import com.skrpld.matule.ui.screens.home.HomeScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    val navigation = remember(navController) { AppNavigation(navController) }

    val authViewModel: AuthViewModel = hiltViewModel()

    val authState = AuthState.LoggedIn // by authViewModel.currentAuthState.collectAsState()

    LaunchedEffect(authState) {
        if (authState is AuthState.LoggedIn) {
            navigation.navigateToHome()
        }
    }

    NavHost(
        navController = navController,
        startDestination = AppDestinations.LOGIN_ROUTE
    ) {
        composable(AppDestinations.LOGIN_ROUTE) {
            LoginScreen(
                viewModel = authViewModel,
                onNavigateToSignup = { navigation.navigateToSignupProfile() }
            )
        }

        composable(AppDestinations.SIGNUP_PROFILE_ROUTE) {
            SignupProfileScreen(
                viewModel = authViewModel,
                onNext = { navigation.navigateToSignupPassword() }
            )
        }

        composable(AppDestinations.SIGNUP_PASSWORD_ROUTE) {
            SignupPasswordScreen(
                viewModel = authViewModel,
                onNext = { navigation.navigateToSignupLock() }
            )
        }

        composable(AppDestinations.SIGNUP_LOCK_ROUTE) {
            LockScreen(
                viewModel = authViewModel,
                mode = LockScreenMode.Signup
            )
        }

        composable(AppDestinations.LOCK_ROUTE) {
            LockScreen(
                viewModel = authViewModel,
                mode = LockScreenMode.Login
            )
        }

        composable(AppDestinations.HOME_ROUTE) {
            HomeScreen()
        }
    }
}