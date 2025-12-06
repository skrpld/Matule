package com.skrpld.matule.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    val isLoggedIn = false

    val startDestination =
        if (isLoggedIn) {AppDestinations.LOCK_ROUTE}
        else {AppDestinations.LOGIN_ROUTE}

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

    }
}