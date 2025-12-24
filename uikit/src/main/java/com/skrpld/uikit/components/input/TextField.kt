package com.skrpld.uikit.components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skrpld.uikit.theme.Accent
import com.skrpld.uikit.theme.Caption
import com.skrpld.uikit.theme.Error
import com.skrpld.uikit.theme.Icons
import com.skrpld.uikit.theme.InputBackground
import com.skrpld.uikit.R

@Composable
fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    error: String? = null,
    isPassword: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    var isFocused by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(!isPassword) }

    val hasError = error != null

    val backgroundColor = when {
        hasError -> Error.copy(alpha = 0.1f)
        else -> InputBackground
    }

    val borderColor = when {
        hasError -> Error
        isFocused -> Accent
        else -> Icons
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        if (label != null) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.bodyLarge,
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else visualTransformation,
            keyboardOptions = keyboardOptions,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { isFocused = it.isFocused },
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .background(backgroundColor, RoundedCornerShape(12.dp))
                        .border(1.dp, borderColor, RoundedCornerShape(12.dp))
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (value.isEmpty() && placeholder != null) {
                            Text(
                                text = placeholder,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Caption
                            )
                        }
                        innerTextField()
                    }

                    if (isPassword) {
                        IconButton(
                            onClick = { passwordVisible = !passwordVisible },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(painter = painterResource(id = if (passwordVisible) R.drawable.ic_eye_on else R.drawable.ic_eye_off), contentDescription = null)
                        }
                    }
                }
            }
        )

        if (hasError) {
            Text(
                text = error,
                style = MaterialTheme.typography.labelLarge.copy(color = Error),
                modifier = Modifier.padding(top = 6.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppTextFieldsGalleryPreview() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        TextField(
            value = "",
            onValueChange = {},
            placeholder = "Введите имя"
        )
        TextField(
            value = "Иван",
            onValueChange = {}
        )
        TextField(
            label = "Имя",
            value = "",
            onValueChange = {},
            placeholder = "Введите имя"
        )
        TextField(
            label = "Имя",
            value = "",
            onValueChange = {},
            placeholder = "Имя",
            error = "Введите ваше имя"
        )
        TextField(
            label = "Имя",
            value = "",
            onValueChange = {},
            placeholder = "Введите имя"
        )
        TextField(
            label = "Имя",
            value = "Введите имя",
            onValueChange = {}
        )
        TextField(
            value = "password123",
            onValueChange = {},
            isPassword = true
        )
        TextField(
            value = "",
            onValueChange = {},
            placeholder = "--.--.----"
        )
    }
}