package com.skrpld.matule.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.skrpld.uikit.components.buttons.ButtonStyle
import com.skrpld.uikit.components.buttons.CommonButton
import com.skrpld.uikit.components.input.TextField
import com.skrpld.uikit.theme.Caption
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignupPasswordScreen(
    onNext: () -> Unit,
    viewModel: AuthViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val password = viewModel.passwordInput
    val confirmedPassword = viewModel.confirmPasswordInput

    val passwordPattern = remember { Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$") }
    val isPasswordValid = password.matches(passwordPattern)
    val isPasswordsMatch = password == confirmedPassword && confirmedPassword.isNotEmpty()

    val isFormValid = isPasswordValid && isPasswordsMatch

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "🖐 Создание пароля",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Введите новый пароль",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Caption
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextField(
                    label = "Новый пароль",
                    value = password,
                    onValueChange = { viewModel.passwordInput = it },
                    placeholder = "••••••••",
                    isPassword = true,
                    error = if (password.isNotEmpty() && !isPasswordValid)
                        "Пароль должен содержать мин. 8 символов, буквы и цифры"
                    else null
                )

                TextField(
                    label = "Повторите пароль",
                    value = confirmedPassword,
                    onValueChange = { viewModel.confirmPasswordInput = it },
                    placeholder = "••••••••",
                    isPassword = true,
                    error = if (confirmedPassword.isNotEmpty() && !isPasswordsMatch)
                        "Пароли не совпадают"
                    else null
                )

                Spacer(modifier = Modifier.height(8.dp))

                CommonButton(
                    text = "Далее",
                    style = if (isFormValid) ButtonStyle.Active else ButtonStyle.Inactive,
                    onClick = {
                        if (isFormValid) {
                            onNext()
                        } else {
                            val message = when {
                                !isPasswordValid -> "Пароль слишком простой"
                                !isPasswordsMatch -> "Пароли не совпадают"
                                else -> "Заполните все поля"
                            }
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.weight(2f))
        }
    }
}