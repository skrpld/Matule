package com.skrpld.uikit.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skrpld.uikit.theme.Accent
import com.skrpld.uikit.theme.AccentInactive
import com.skrpld.uikit.theme.Black
import com.skrpld.uikit.theme.InputBackground
import com.skrpld.uikit.theme.White

enum class ButtonStyle(
    val containerColor: Color,
    val contentColor: Color,
    val borderColor: Color = Color.Transparent
) {
    Active(
        containerColor = Accent,
        contentColor = White
    ),
    Inactive(
        containerColor = AccentInactive,
        contentColor = White
    ),
    Outline(
        containerColor = Color.White,
        contentColor = Accent,
        borderColor = Accent
    ),
    Neutral(
        containerColor = InputBackground,
        contentColor = Black
    )
}

@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    text: String = "Подтвердить",
    isSmall: Boolean = false,
    style: ButtonStyle = ButtonStyle.Active,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(if (!isSmall) 56.dp else 40.dp)
            .then(
                if (!isSmall) Modifier.fillMaxWidth() else Modifier.width(96.dp)
            ),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = style.containerColor,
            contentColor = style.contentColor,
            disabledContainerColor = style.containerColor,
            disabledContentColor = style.contentColor
        ),
        border = BorderStroke(1.dp, style.borderColor),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        Text(
            text = text,
            style = if (!isSmall) MaterialTheme.typography.titleSmall else MaterialTheme.typography.labelLarge,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CommonButtonPreview() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Big", style = MaterialTheme.typography.titleLarge)

            CommonButton(style = ButtonStyle.Active)
            CommonButton(style = ButtonStyle.Inactive)
            CommonButton(style = ButtonStyle.Outline)
            CommonButton(style = ButtonStyle.Neutral)
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Small", style = MaterialTheme.typography.titleLarge)

            CommonButton(isSmall = true, style = ButtonStyle.Active, text = "Добавить")
            CommonButton(isSmall = true, style = ButtonStyle.Outline, text = "Убрать")
            CommonButton(isSmall = true, style = ButtonStyle.Inactive, text = "Добавить")
            CommonButton(isSmall = true, style = ButtonStyle.Neutral)
        }
    }
}