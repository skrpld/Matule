package com.skrpld.matule.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.skrpld.matule.R
import com.skrpld.matule.data.models.Product
import com.skrpld.matule.data.models.Promotion
import com.skrpld.matule.ui.components.AppBottomBar
import com.skrpld.matule.ui.theme.*

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val promotions by viewModel.promotions.collectAsState()
    val filteredProducts = viewModel.filteredProducts // derivedState не требует collectAsState внутри Composable, если читается value, но здесь мы используем свойство by derivedStateOf

    Scaffold(
        bottomBar = { AppBottomBar(navController) },
        containerColor = InputBackground // Фон всего экрана (светло-серый)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Отступ сверху
            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Поиск
            item {
                SearchBar(
                    value = viewModel.searchQuery,
                    onValueChange = { viewModel.searchQuery = it }
                )
            }

            // Блок Акции
            item {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "Акции и новости",
                        style = Typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Black
                    )
                    PromotionsRow(promotions = promotions)
                }
            }

            // Блок Каталог (Фильтры)
            item {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "Каталог описаний",
                        style = Typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Black
                    )
                    CategoriesRow(
                        categories = viewModel.categories,
                        selectedCategory = viewModel.selectedCategory,
                        onCategorySelect = viewModel::onCategorySelected
                    )
                }
            }

            // Список товаров
            items(filteredProducts) { product ->
                ProductCard(
                    product = product,
                    onAddClick = { viewModel.onAddToCart(product.id) }
                )
            }

            // Отступ снизу
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

// --- Компоненты ---

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp)),
        placeholder = {
            Text(text = "Искать описания", style = Typography.bodyLarge, color = Caption)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Caption
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = White,
            unfocusedContainerColor = White,
            disabledContainerColor = White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        singleLine = true
    )
}

@Composable
fun PromotionsRow(promotions: List<Promotion>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(promotions) { promo ->
            Box(
                modifier = Modifier
                    .width(260.dp) // Ширина карточки акции
                    .height(150.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(promo.backgroundColor))
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.align(Alignment.CenterStart)) {
                    Text(
                        text = promo.title,
                        style = Typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = White
                        )
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = promo.price,
                        style = Typography.titleLarge.copy(color = White)
                    )
                }

                // Изображение (Заглушка, так как нет реальных ресурсов)
                // В реальном коде: Image(painter = painterResource(id = R.drawable.bottle)...)
                // Для примера используем иконку
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground), // Заглушка
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(100.dp)
                        .offset(x = 10.dp, y = 10.dp), // Сдвиг для эффекта
                    contentScale = ContentScale.Fit,
                    alpha = 0.5f // Прозрачность т.к. это заглушка
                )
            }
        }
    }
}

@Composable
fun CategoriesRow(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelect: (String) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categories) { category ->
            val isSelected = category == selectedCategory
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isSelected) Accent else White)
                    .clickable { onCategorySelect(category) }
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = category,
                    style = Typography.bodyLarge.copy(
                        fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
                    ),
                    color = if (isSelected) White else Black
                )
            }
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    onAddClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // На макете плоские карточки
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = product.title,
                style = Typography.titleMedium.copy(fontSize = 16.sp, lineHeight = 20.sp),
                color = Black,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = product.category,
                style = Typography.labelMedium,
                color = Description
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.price,
                    style = Typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = Black
                )
                Button(
                    onClick = onAddClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Accent),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
                    modifier = Modifier.height(40.dp)
                ) {
                    Text(
                        text = "Добавить",
                        style = Typography.labelLarge.copy(color = White, fontWeight = FontWeight.Medium)
                    )
                }
            }
        }
    }
}