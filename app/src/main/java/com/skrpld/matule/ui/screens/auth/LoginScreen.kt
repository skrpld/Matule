package com.skrpld.matule.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.skrpld.uikit.components.buttons.ButtonStyle
import com.skrpld.uikit.components.buttons.CommonButton
import com.skrpld.uikit.components.buttons.LoginButton
import com.skrpld.uikit.components.input.TextField
import org.koin.androidx.compose.koinViewModel
import com.skrpld.uikit.R as UiKitR

@Composable
fun LoginScreen(
    viewModel: AuthViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit,
    onNavigateToSignup: () -> Unit
) {
    val email = viewModel.emailInput
    val password = viewModel.passwordInput

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "🖐 Добро пожаловать!",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Войдите, чтобы пользоваться функциями приложения",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                TextField(
                    label = "Вход по E-mail",
                    value = email,
                    onValueChange = { viewModel.emailInput = it },
                    placeholder = "example@mail.com"
                )

                TextField(
                    label = "Пароль",
                    value = password,
                    onValueChange = { viewModel.passwordInput = it },
                    placeholder = "********",
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                CommonButton(
                    text = "Войти",
                    style = ButtonStyle.Active,
                    onClick = {
                        onLoginSuccess()
                    }
                )

                TextButton(
                    onClick = onNavigateToSignup,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Зарегистрироваться",
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Или войдите с помощью",
                    style = MaterialTheme.typography.labelLarge
                )

                LoginButton(
                    text = "Войти с VK",
                    icon = UiKitR.drawable.ic_vk,
                    onClick = { onLoginSuccess() }
                )

                LoginButton(
                    text = "Войти с Yandex",
                    icon = UiKitR.drawable.ic_yandex,
                    onClick = { onLoginSuccess() }
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}