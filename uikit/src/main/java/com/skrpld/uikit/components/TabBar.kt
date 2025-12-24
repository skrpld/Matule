package com.skrpld.uikit.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skrpld.uikit.R
import com.skrpld.uikit.theme.Accent
import com.skrpld.uikit.theme.Icons
import com.skrpld.uikit.theme.MatuleTheme
import com.skrpld.uikit.theme.White

@Composable
fun TabBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar(
        containerColor = White
    ) {
        val items = listOf(
            BottomNavItem("Главная", R.drawable.ic_home),
            BottomNavItem("Каталог", R.drawable.ic_catalog),
            BottomNavItem("Проекты", R.drawable.ic_projects),
            BottomNavItem("Профиль", R.drawable.ic_profile),
        )

        items.forEachIndexed { index, item ->
            val isSelected = selectedIndex == index

            NavigationBarItem(
                selected = isSelected,
                onClick = { onItemSelected(index) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = if (isSelected) Accent else Icons
                        )
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Accent,
                    unselectedIconColor = Icons,
                    selectedTextColor = Accent,
                    unselectedTextColor = Icons,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

private data class BottomNavItem(
    val title: String,
    val iconRes: Int
)

@Preview(showBackground = true)
@Composable
fun TabBarHomePreview() {
    MatuleTheme {
        Surface {
            TabBar(
                selectedIndex = 0,
                onItemSelected = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TabBarProjectsPreview() {
    MatuleTheme {
        Surface {
            TabBar(
                selectedIndex = 2,
                onItemSelected = {}
            )
        }
    }
}