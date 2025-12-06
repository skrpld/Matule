package com.skrpld.matule.ui.navigation

import androidx.navigation.NavHostController

class AppNavigation(
    private val navController: NavHostController
) {
    /**
     * App start
     */
    fun navigateToSplash() {
        navController.navigate(AppDestinations.SPLASH_ROUTE)
    }

    fun navigateToLock() {
        navController.navigate(AppDestinations.LOCK_ROUTE)
    }

    /**
     * Login / Signup
     */
    fun navigateToLogin() {
        navController.navigate(AppDestinations.LOGIN_ROUTE) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    fun navigateToSignupProfile() {
        navController.navigate(AppDestinations.SIGNUP_PROFILE_ROUTE)
    }

    fun navigateToSignupPassword() {
        navController.navigate(AppDestinations.SIGNUP_PASSWORD_ROUTE)
    }

    fun navigateToSignupLock() {
        navController.navigate(AppDestinations.SIGNUP_LOCK_ROUTE)
    }

    /**
     * Main
     */
    fun navigateToHome() {
        navController.navigate(AppDestinations.HOME_ROUTE) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    fun navigateToCatalog() {
        navController.navigate(AppDestinations.CATALOG_ROUTE)
    }

    fun navigateToCart() {
        navController.navigate(AppDestinations.CART_ROUTE)
    }

    fun navigateToProfile() {
        navController.navigate(AppDestinations.PROFILE_ROUTE)
    }

    fun navigateToProjects() {
        navController.navigate(AppDestinations.PROJECTS_ROUTE)
    }

    fun navigateToCreateProject() {
        navController.navigate(AppDestinations.PROJECT_CREATE_ROUTE)
    }

    /**
     * Utils
     */
    fun onBack() {
        navController.popBackStack()
    }
}