package com.skrpld.matule.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.skrpld.matule.ui.components.auth.CustomTextField
import com.skrpld.matule.ui.components.auth.NextButton

@Composable
fun SignupProfileScreen(
    viewModel: AuthViewModel,
    onNext: () -> Unit
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val firstName = viewModel.firstNameInput
    val lastName = viewModel.lastNameInput
    val surName = viewModel.surNameInput
    val birthDate = viewModel.birthDateInput
    val gender = viewModel.genderInput
    val email = viewModel.emailInput

    val emailPattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$")

    val isFormValid =
        firstName.isNotEmpty() &&
                lastName.isNotEmpty() &&
                surName.isNotEmpty() &&
                birthDate.isNotEmpty() && // TODO: написать паттерн
                gender.isNotEmpty() &&
                email.matches(emailPattern)

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Column {
                Text(
                    text = "Создание Профиля",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Без профиля вы не сможете создавать проекты.\n" +
                            "В профиле будут храниться результаты проектов и ваши описания.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column {
                CustomTextField(
                    value = firstName,
                    onValueChange = { viewModel.firstNameInput = it },
                    placeholder = "Имя"
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    value = surName,
                    onValueChange = { viewModel.surNameInput = it },
                    placeholder = "Отчество"
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    value = lastName,
                    onValueChange = { viewModel.lastNameInput = it },
                    placeholder = "Фамилия"
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    value = birthDate,
                    onValueChange = { viewModel.birthDateInput = it },
                    placeholder = "Дата рождения"
                )

                Spacer(modifier = Modifier.height(16.dp))

                GenderDropdownField(
                    selectedGender = gender,
                    onGenderSelected = { viewModel.genderInput = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    value = email,
                    onValueChange = { viewModel.emailInput = it },
                    placeholder = "Почта"
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            NextButton(
                isFormValid = isFormValid,
                onClick = {
                    if (isFormValid) {
                        viewModel.onSignupProfile()
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
    }
}

@Composable
fun GenderDropdownField(
    selectedGender: String,
    onGenderSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val genderOptions = listOf("Мужской", "Женский")

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomTextField(
            value = selectedGender,
            onValueChange = {},
            placeholder = "Пол"
        )

        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 12.dp),
            tint = Color.Gray
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable { expanded = !expanded }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            genderOptions.forEach { gender ->
                DropdownMenuItem(
                    text = { Text(text = gender) },
                    onClick = {
                        onGenderSelected(gender)
                        expanded = false
                    }
                )
            }
        }
    }
}