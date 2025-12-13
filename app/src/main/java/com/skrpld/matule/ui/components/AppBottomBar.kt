package com.skrpld.matule.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.skrpld.matule.R
import com.skrpld.matule.ui.theme.Accent
import com.skrpld.matule.ui.theme.InputIcon
import com.skrpld.matule.ui.theme.White

@Composable
fun AppBottomBar() {
    NavigationBar(
        containerColor = White,
        tonalElevation = 8.dp
    ) {
        val items = listOf("Главная", "Каталог", "Проекты", "Профиль")
        val icons = listOf(
            R.drawable.ic_home,
            R.drawable.ic_catalog,
            R.drawable.ic_project,
            R.drawable.ic_profile,
        )

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    val isSelected = index == 3
                    Icon(
                        painter = painterResource(id = icons[index]),
                        contentDescription = item,
                        modifier = Modifier.Companion.size(24.dp),
                        tint = if (isSelected) Accent else InputIcon
                    )
                },
                selected = index == 3, // TODO: индекс выбраной страницы
                label = {
                    Text(
                        text = item,
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                onClick = { TODO("Переход на страницу") },
            )
        }
    }
}