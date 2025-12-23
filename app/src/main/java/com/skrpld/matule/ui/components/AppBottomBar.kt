package com.skrpld.matule.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.skrpld.matule.R
import com.skrpld.matule.ui.navigation.AppDestinations
import com.skrpld.uikit.theme.Accent
import com.skrpld.uikit.theme.InputIcon
import com.skrpld.uikit.theme.White

@Composable
fun AppBottomBar(
    navController: NavController
) {
    NavigationBar(
        containerColor = White,
        tonalElevation = 8.dp
    ) {
        // Получаем текущий маршрут для определения выделенного элемента
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val items = listOf("Главная", "Каталог", "Проекты", "Профиль")
        val icons = listOf(
            R.drawable.ic_home,
            R.drawable.ic_catalog,
            R.drawable.ic_project,
            R.drawable.ic_profile,
        )
        // Соответствующие маршруты для каждой вкладки
        val routes = listOf(
            AppDestinations.HOME_ROUTE,
            AppDestinations.CATALOG_ROUTE,
            AppDestinations.PROJECTS_ROUTE,
            AppDestinations.PROFILE_ROUTE
        )

        items.forEachIndexed { index, item ->
            val targetRoute = routes[index]
            // Проверяем, является ли текущий маршрут выбранным
            val isSelected = currentRoute == targetRoute

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = icons[index]),
                        contentDescription = item,
                        modifier = Modifier.size(24.dp),
                        tint = if (isSelected) Accent else InputIcon
                    )
                },
                selected = isSelected,
                label = {
                    Text(
                        text = item,
                        style = MaterialTheme.typography.labelMedium,
                        color = if (isSelected) Accent else InputIcon
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent // Убираем фоновую "таблетку" при выделении
                ),
                onClick = {
                    // Логика навигации BottomBar
                    navController.navigate(targetRoute) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
            )
        }
    }
}