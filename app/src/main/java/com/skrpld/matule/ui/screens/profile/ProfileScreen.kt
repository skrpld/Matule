package com.skrpld.matule.ui.screens.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.skrpld.matule.ui.components.AppBottomBar
import com.skrpld.matule.ui.theme.Accent
import com.skrpld.matule.ui.theme.Caption
import com.skrpld.matule.ui.theme.Error
import com.skrpld.matule.ui.theme.White

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val firstName = viewModel.firstName
    val email = viewModel.email
    val showNotification = viewModel.showNotifications

    Scaffold(
        bottomBar = { AppBottomBar(navController) },
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

            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = firstName,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = email,
                    style = MaterialTheme.typography.bodyLarge.copy(color = Caption)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { /* TODO: Навигация на заказы */ },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "📋", fontSize = 32.sp) //TODO: Должна быть иконка

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "Мои заказы",
                        style = MaterialTheme.typography.titleSmall
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "⚙️", fontSize = 32.sp) //TODO: Должна быть иконка

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "Уведомления",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleSmall
                    )

                    Switch(
                        checked = showNotification,
                        onCheckedChange = {
                            viewModel.toggleNotifications()
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = White,
                            checkedTrackColor = Accent,
                            uncheckedThumbColor = White,
                            uncheckedTrackColor = Caption
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Политика конфиденциальности",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Caption),
                    modifier = Modifier.clickable {
                        TODO("Политика конфиденциальности")
                    }
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Пользовательское соглашение",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Caption),
                    modifier = Modifier.clickable {
                        TODO("Пользовательское соглашение")
                    }
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Выход",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Error),
                    modifier = Modifier.clickable {
                        viewModel.logout()
                    }
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}