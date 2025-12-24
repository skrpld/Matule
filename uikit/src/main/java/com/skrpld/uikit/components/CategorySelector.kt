package com.skrpld.uikit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.skrpld.uikit.components.buttons.Chip
import com.skrpld.uikit.components.buttons.ChipsStyle

@Composable
fun CategorySelector(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            val style = if (category == selectedCategory) {
                ChipsStyle.Active
            } else {
                ChipsStyle.Inactive
            }

            Chip(
                text = category,
                style = style,
                onClick = { onCategorySelected(category) }
            )
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun CategorySelectorPreview() {
    val categories = listOf("Популярные", "Женщинам", "Мужчинам", "Детям", "Аксессуары")
    val selected = "Популярные"

    Column(
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        CategorySelector(
            categories = categories,
            selectedCategory = selected,
            onCategorySelected = { /* Обработка нажатия */ }
        )
    }
}