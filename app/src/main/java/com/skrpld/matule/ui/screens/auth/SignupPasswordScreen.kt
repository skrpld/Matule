package com.skrpld.matule.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.skrpld.matule.ui.components.auth.CustomTextField
import com.skrpld.matule.ui.components.auth.NextButton

@Composable
fun SignupPasswordScreen(
    viewModel: AuthViewModel,
    onNext: () -> Unit
) {
    var password = viewModel.passwordInput
    var confirmedPassword = viewModel.confirmPasswordInput

    val passwordPattern = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$")

    val isPasswordValid = password.matches(passwordPattern)
    val isPasswordsMatch = password == confirmedPassword

    val isFormValid = isPasswordValid && isPasswordsMatch
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
                    text = "🖐 Создание пароля",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Введите новый пароль",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column {    // Input
                Text(
                    text = "Новый пароль",
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

                Text(
                    text = "Повторяйте пароль",
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                CustomTextField(
                    value = confirmedPassword,
                    onValueChange = {
                        viewModel.confirmPasswordInput = it
                    },
                    placeholder = "",
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                NextButton(
                    isFormValid = isFormValid,
                    onClick = {
                        if (isFormValid) {
                            onNext()
                        } else {
                            Toast.makeText(
                                context,
                                "Поля не заполнены или формат данных не верный",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.weight(3f))
        }
    }
}