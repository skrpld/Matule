package com.skrpld.matule.ui.screens.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.skrpld.uikit.R
import com.skrpld.uikit.components.TabBar
import com.skrpld.uikit.components.controls.Toggle
import com.skrpld.uikit.theme.Caption
import com.skrpld.uikit.theme.Error
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    onNavigateToTab: (Int) -> Unit,
    viewModel: ProfileViewModel = koinViewModel()
) {
    val firstName = viewModel.firstName
    val email = viewModel.email
    val showNotification = viewModel.showNotifications

    Scaffold(
        bottomBar = {
            TabBar(
                selectedIndex = 3,
                onItemSelected = { index -> onNavigateToTab(index) }
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = firstName,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = email,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Caption
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Column(verticalArrangement = Arrangement.spacedBy(30.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* TODO: Навигация на заказы */ },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.order),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Black
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "Мои заказы",
                        style = MaterialTheme.typography.titleSmall
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Black
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "Уведомления",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleSmall
                    )

                    Toggle(
                        checked = showNotification,
                        onCheckedChange = { viewModel.toggleNotifications() }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Text(
                    text = "Политика конфиденциальности",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Caption,
                    modifier = Modifier.clickable {
                        /* TODO: Открыть политику */
                    }
                )

                Text(
                    text = "Пользовательское соглашение",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Caption,
                    modifier = Modifier.clickable {
                        /* TODO: Открыть соглашение */
                    }
                )

                Text(
                    text = "Выход",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Error,
                    modifier = Modifier.clickable {
                        viewModel.logout()
                    }
                )
            }
        }
    }
}