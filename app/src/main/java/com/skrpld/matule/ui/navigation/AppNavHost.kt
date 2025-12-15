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
import com.skrpld.matule.ui.screens.profile.ProfileScreen
import com.skrpld.matule.ui.screens.projects.ProjectsScreen

// Для примера заглушка каталога, если файла нет
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.skrpld.matule.ui.components.AppBottomBar

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    val navigation = remember(navController) { AppNavigation(navController) }
    val authViewModel: AuthViewModel = hiltViewModel()

    // Пример отслеживания состояния авторизации
    // val authState by authViewModel.currentAuthState.collectAsState()
    // В вашем коде было закомментировано, оставляю логику как есть

    NavHost(
        navController = navController,
        startDestination = AppDestinations.HOME_ROUTE // Или SPLASH, если есть
    ) {
        // --- AUTH ---
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
            LockScreen(viewModel = authViewModel, mode = LockScreenMode.Signup)
        }
        composable(AppDestinations.LOCK_ROUTE) {
            LockScreen(viewModel = authViewModel, mode = LockScreenMode.Login)
        }

        // --- MAIN TABS ---

        composable(AppDestinations.HOME_ROUTE) {
            // Передаем navController в HomeScreen
            HomeScreen(navController = navController)
        }

        composable(AppDestinations.CATALOG_ROUTE) {
            // Заглушка или ваш CatalogScreen(navController = navController)
            CatalogScreenStub(navController = navController)
        }

        composable(AppDestinations.PROJECTS_ROUTE) {
            ProjectsScreen(navController = navController)
        }

        composable(AppDestinations.PROFILE_ROUTE) {
            ProfileScreen(navController = navController)
        }

        // --- OTHER ---
        composable(AppDestinations.PROJECT_CREATE_ROUTE) {
            // CreateProjectScreen(...)
        }

        // ... остальные экраны (Cart и т.д.)
    }
}

// Временная заглушка для каталога
@Composable
fun CatalogScreenStub(navController: NavHostController) {
    Scaffold(bottomBar = { AppBottomBar(navController) }) {
        Box(modifier = Modifier.fillMaxSize().padding(it), contentAlignment = Alignment.Center) {
            Text("Каталог")
        }
    }
}