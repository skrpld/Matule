package com.skrpld.matule.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.skrpld.uikit.components.buttons.ButtonStyle
import com.skrpld.uikit.components.buttons.CommonButton
import com.skrpld.uikit.components.input.TextField
import com.skrpld.uikit.theme.Caption
import org.koin.androidx.compose.koinViewModel
import com.skrpld.uikit.R

@Composable
fun SignupProfileScreen(
    onNext: () -> Unit,
    viewModel: AuthViewModel = koinViewModel()
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val firstName = viewModel.firstNameInput
    val lastName = viewModel.lastNameInput
    val surName = viewModel.surNameInput
    val birthDate = viewModel.birthDateInput
    val gender = viewModel.genderInput
    val email = viewModel.emailInput

    val emailPattern = remember { Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$") }

    val isFormValid = remember(firstName, lastName, surName, birthDate, gender, email) {
        firstName.isNotEmpty() &&
                lastName.isNotEmpty() &&
                surName.isNotEmpty() &&
                birthDate.isNotEmpty() &&
                gender.isNotEmpty() &&
                email.matches(emailPattern)
    }

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

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Без профиля вы не сможете создавать проекты.\n" +
                            "В профиле будут храниться результаты проектов и ваши описания.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Caption
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                TextField(
                    label = "Имя",
                    value = firstName,
                    onValueChange = { viewModel.firstNameInput = it },
                    placeholder = "Введите имя"
                )

                TextField(
                    label = "Отчество",
                    value = surName,
                    onValueChange = { viewModel.surNameInput = it },
                    placeholder = "Введите отчество"
                )

                TextField(
                    label = "Фамилия",
                    value = lastName,
                    onValueChange = { viewModel.lastNameInput = it },
                    placeholder = "Введите фамилию"
                )

                TextField(
                    label = "Дата рождения",
                    value = birthDate,
                    onValueChange = { viewModel.birthDateInput = it },
                    placeholder = "ДД.ММ.ГГГГ",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                GenderDropdownField(
                    selectedGender = gender,
                    onGenderSelected = { viewModel.genderInput = it }
                )

                TextField(
                    label = "Почта",
                    value = email,
                    onValueChange = { viewModel.emailInput = it },
                    placeholder = "example@mail.com",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(24.dp))

            CommonButton(
                text = "Далее",
                style = if (isFormValid) ButtonStyle.Active else ButtonStyle.Inactive,
                onClick = {
                    if (isFormValid) {
                        onNext()
                    } else {
                        Toast.makeText(
                            context,
                            "Поля не заполнены или формат данных неверный",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))
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
        TextField(
            label = "Пол",
            value = selectedGender,
            onValueChange = {},
            placeholder = "Выберите пол"
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_chevron_down),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(top = 28.dp)
                .padding(end = 16.dp),
            tint = Caption
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
                .fillMaxWidth(0.85f)
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