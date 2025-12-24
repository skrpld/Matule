package com.skrpld.matule.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.skrpld.matule.R
import com.skrpld.matule.data.models.Product
import com.skrpld.matule.data.models.Promotion
import com.skrpld.uikit.components.CategorySelector
import com.skrpld.uikit.components.TabBar
import com.skrpld.uikit.components.buttons.ButtonStyle
import com.skrpld.uikit.components.buttons.CommonButton
import com.skrpld.uikit.components.input.SearchField
import com.skrpld.uikit.theme.Black
import com.skrpld.uikit.theme.Description
import com.skrpld.uikit.theme.InputBackground
import com.skrpld.uikit.theme.White
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    onNavigateToTab: (Int) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val promotions by viewModel.promotions.collectAsState()
    val filteredProducts = viewModel.filteredProducts

    Scaffold(
        bottomBar = {
            TabBar(
                selectedIndex = 0,
                onItemSelected = { index -> onNavigateToTab(index) }
            )
        },
        containerColor = InputBackground
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                SearchField(
                    value = viewModel.searchQuery,
                    onValueChange = { viewModel.searchQuery = it },
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "Акции и новости",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(horizontal = 20.dp),
                        color = Black
                    )
                    PromotionsRow(promotions = promotions)
                }
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "Каталог описаний",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(horizontal = 20.dp),
                        color = Black
                    )
                    CategorySelector(
                        categories = viewModel.categories,
                        selectedCategory = viewModel.selectedCategory,
                        onCategorySelected = { viewModel.onCategorySelected(it) }
                    )
                }
            }

            items(
                items = filteredProducts,
                key = { it.id }
            ) { product ->
                Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                    ProductCard(
                        product = product,
                        onAddClick = { viewModel.onAddToCart(product.id) }
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun PromotionsRow(promotions: List<Promotion>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        items(
            items = promotions,
            key = { it.id }
        ) { promo ->
            Box(
                modifier = Modifier
                    .width(260.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(promo.backgroundColor))
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.align(Alignment.CenterStart)) {
                    Text(
                        text = promo.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = White
                        )
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = promo.price,
                        style = MaterialTheme.typography.titleLarge.copy(color = White)
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(100.dp)
                        .offset(x = 10.dp, y = 10.dp),
                    contentScale = ContentScale.Fit,
                    alpha = 0.5f
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
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Black,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = product.category,
                style = MaterialTheme.typography.labelMedium,
                color = Description
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.price,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = Black
                )

                CommonButton(
                    text = "Добавить",
                    isSmall = true,
                    style = ButtonStyle.Active,
                    onClick = onAddClick,
                    modifier = Modifier.width(110.dp)
                )
            }
        }
    }
}