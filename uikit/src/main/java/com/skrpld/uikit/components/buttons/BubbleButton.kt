package com.skrpld.uikit.components.buttons

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skrpld.uikit.R
import com.skrpld.uikit.theme.Description
import com.skrpld.uikit.theme.InputBackground

@Composable
fun BubbleButton(
    icon: Int,
    modifier: Modifier = Modifier,
    isSmall: Boolean = false,
    onClick: () -> Unit = {}
) {
    val size = if (!isSmall) 48.dp else 32.dp

    Surface(
        onClick = onClick,
        modifier = modifier.size(size),
        shape = RoundedCornerShape(12.dp),
        color = InputBackground
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = Description
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IconButtonsPreview() {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BubbleButton(
            icon = R.drawable.ic_chevron_left,
            isSmall = true,
            onClick = { /* Назад */ }
        )

        BubbleButton(
            icon = R.drawable.ic_filter,
            isSmall = false,
            onClick = { /* Открыть фильтры */ }
        )
    }
}