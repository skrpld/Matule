package com.skrpld.uikit.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skrpld.uikit.theme.Accent
import com.skrpld.uikit.theme.Description
import com.skrpld.uikit.theme.InputBackground
import com.skrpld.uikit.theme.White

enum class ChipsStyle(
    val containerColor: Color,
    val contentColor: Color,
    val borderColor: Color = Color.Transparent
) {
    Active(
        containerColor = Accent,
        contentColor = White
    ),
    Inactive(
        containerColor = InputBackground,
        contentColor = Description
    )
}

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String,
    style: ChipsStyle = ChipsStyle.Active,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(129.dp)
            .height(48.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = style.containerColor,
            contentColor = style.contentColor,
            disabledContainerColor = style.containerColor,
            disabledContentColor = style.contentColor
        ),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChipsPreview() {
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
            Chip(text = "Популярное", style = ChipsStyle.Active)
            Chip(text = "Популярное", style = ChipsStyle.Inactive)
        }
    }
}