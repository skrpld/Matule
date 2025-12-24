package com.skrpld.matule.ui.screens.auth

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.skrpld.matule.R
import com.skrpld.matule.ui.components.auth.CustomTextField
import com.skrpld.matule.ui.components.auth.NextButton

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onNavigateToSignup: () -> Unit
) {
    var email = viewModel.emailInput
    var password = viewModel.passwordInput

    val emailPattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$")

    val passwordPattern = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$")

    val isEmailValid = email.matches(emailPattern)
    val isPasswordValid = password.matches(passwordPattern)

    val isFormValid = isEmailValid && isPasswordValid
    val context = LocalContext.current

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            Column(    // Text
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "🖐 Добро пожаловать!",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Войдите, чтобы пользоваться функциями приложения",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column {    // Input
                Text(
                    text = "Вход по E-mail",
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                CustomTextField(
                    value = email,
                    onValueChange = {
                        viewModel.emailInput = it
                    },
                    placeholder = "example@mail.com",
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Пароль",
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                CustomTextField(
                    value = password,
                    onValueChange = {
                        viewModel.passwordInput = it
                    },
                    placeholder = "",
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                NextButton(
                    isFormValid = isFormValid,
                    onClick = {
                        if (isFormValid) {
                            viewModel.onLogin()
                        } else {
                            Toast.makeText(
                                context,
                                "Поля не заполнены или формат данных не верный",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                )

                Spacer(modifier = Modifier.height(4.dp))

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

            Column(   // Alternative methods
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Или войдите с помощью",
                    style = MaterialTheme.typography.labelLarge
                )

                Spacer(modifier = Modifier.height(24.dp))

                SocialButton("Войти с VK", R.drawable.ic_vk, { TODO("Вход через VK") })
                Spacer(modifier = Modifier.height(12.dp))
                SocialButton("Войти с Yandex", R.drawable.ic_yandex, { TODO("Вход через Yandex") })
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}