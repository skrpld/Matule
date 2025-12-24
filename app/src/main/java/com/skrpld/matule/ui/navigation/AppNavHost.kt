package com.skrpld.matule.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.skrpld.matule.ui.screens.auth.AuthViewModel
import com.skrpld.matule.ui.screens.auth.LockScreen
import com.skrpld.matule.ui.screens.auth.LockScreenMode
import com.skrpld.matule.ui.screens.auth.LoginScreen
import com.skrpld.matule.ui.screens.auth.SignupPasswordScreen
import com.skrpld.matule.ui.screens.auth.SignupProfileScreen
import com.skrpld.matule.ui.screens.home.HomeScreen
import com.skrpld.matule.ui.screens.profile.ProfileScreen
import com.skrpld.matule.ui.screens.projects.ProjectsScreen
import com.skrpld.uikit.components.TabBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    val navigation = remember(navController) { AppNavigation(navController) }
    val authViewModel: AuthViewModel = koinViewModel()

    val onNavigateToTab: (Int) -> Unit = { index ->
        when (index) {
            0 -> navigation.navigateToHome()
            1 -> navigation.navigateToCatalog()
            2 -> navigation.navigateToProjects()
            3 -> navigation.navigateToProfile()
        }
    }

    NavHost(
        navController = navController,
        startDestination = AppDestinations.LOGIN_ROUTE
    ) {
        composable(AppDestinations.LOGIN_ROUTE) {
            LoginScreen(
                viewModel = authViewModel,
                onLoginSuccess = { navigation.navigateToHome() },
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

        composable(AppDestinations.HOME_ROUTE) {
            HomeScreen(onNavigateToTab = onNavigateToTab)
        }

        composable(AppDestinations.CATALOG_ROUTE) {
            CatalogScreenStub(onNavigateToTab = onNavigateToTab)
        }

        composable(AppDestinations.PROJECTS_ROUTE) {
            ProjectsScreen(
                onNavigateToTab = onNavigateToTab,
                onAddProject = { navigation.navigateToCreateProject() }
            )
        }

        composable(AppDestinations.PROFILE_ROUTE) {
            ProfileScreen(onNavigateToTab = onNavigateToTab)
        }

        composable(AppDestinations.PROJECT_CREATE_ROUTE) {

        }
    }
}

@Composable
fun CatalogScreenStub(onNavigateToTab: (Int) -> Unit) {
    Scaffold(
        bottomBar = { TabBar(selectedIndex = 1, onItemSelected = onNavigateToTab) }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it), contentAlignment = Alignment.Center) {
            Text("Экран Каталога")
        }
    }
}