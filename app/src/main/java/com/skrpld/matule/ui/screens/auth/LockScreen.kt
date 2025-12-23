package com.skrpld.matule.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

enum class LockScreenMode {
    Login,
    Signup
}

@Composable
fun LockScreen(
    viewModel: AuthViewModel,
    mode: LockScreenMode = LockScreenMode.Login
) {
    val pinCode = viewModel.pinInput
    val maxPinLength = 4

    LaunchedEffect(pinCode) {
        if (pinCode.length == maxPinLength) {
            when (mode) {
                LockScreenMode.Login -> viewModel.onLoginLock()
                LockScreenMode.Signup -> viewModel.onSignupComplete()
            }
        }
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = if (mode == LockScreenMode.Login) "Вход" else "Создание пароля",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.W700),
                textAlign = TextAlign.Center
            )

            if (mode == LockScreenMode.Signup) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Для защиты ваших персональных данных",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            PasscodeIndicator(
                currentLength = pinCode.length,
                maxLength = maxPinLength
            )

            Spacer(modifier = Modifier.height(48.dp))

            Numpad(
                onNumberClick = { number ->
                    if (pinCode.length < maxPinLength) {
                        viewModel.pinInput += number
                    }
                },
                onDeleteClick = {
                    if (pinCode.isNotEmpty()) {
                        viewModel.pinInput = pinCode.dropLast(1)
                    }
                }
            )

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Composable
fun PasscodeIndicator(
    currentLength: Int,
    maxLength: Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(maxLength) { index ->
            val isFilled = index < currentLength
            val color = MaterialTheme.colorScheme.primary

            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .then(
                        if (isFilled) {
                            Modifier.background(color)
                        } else {
                            Modifier.border(
                                width = 1.dp,
                                color = color,
                                shape = CircleShape
                            )
                        }
                    )
            )
        }
    }
}

@Composable
fun Numpad(
    onNumberClick: (String) -> Unit,
    onDeleteClick: () -> Unit
) {
    val numbers = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf(null, "0", "del")
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        numbers.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                row.forEach { item ->
                    if (item == null) {
                        Spacer(modifier = Modifier.size(80.dp))
                    } else if (item == "del") {
                        NumpadButton(
                            onClick = onDeleteClick
                        )
                    } else {
                        NumpadButton(
                            text = item,
                            onClick = { onNumberClick(item) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NumpadButton(
    text: String? = null,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    val isPressed by interactionSource.collectIsPressedAsState()

    val backgroundColor = if (isPressed) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surface
    }

    val contentColor = if (isPressed) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        if (text.isNullOrEmpty()) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Удалить",
                modifier = Modifier.size(48.dp),
                tint = contentColor
            )
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold,
                color = contentColor
            )
        }
    }
}