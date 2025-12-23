package com.skrpld.uikit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skrpld.uikit.R
import com.skrpld.uikit.theme.Accent
import com.skrpld.uikit.theme.Caption
import com.skrpld.uikit.theme.Icons
import com.skrpld.uikit.theme.InputBackground
import com.skrpld.uikit.theme.InputString
import com.skrpld.uikit.theme.White

@Composable
fun Toggle(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = White,
            checkedTrackColor = Accent,
            checkedBorderColor = Color.Transparent,
            uncheckedThumbColor = White,
            uncheckedTrackColor = InputString,
            uncheckedBorderColor = Color.Transparent
        )
    )
}

@Composable
fun Counter(
    value: Int,
    onValueChange: (Int) -> Unit
) {
    val isMinimum = value <= 1

    Surface(
        color = InputBackground,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(IntrinsicSize.Min)
        ) {
            IconButton(
                onClick = { onValueChange(value - 1) },
                enabled = !isMinimum,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_minus),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = if (!isMinimum) Caption else Icons
                )
            }

            VerticalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp,
                color = InputString
            )

            IconButton(
                onClick = { onValueChange(value + 1) },
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = Caption
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun TogglePreview() {
    var isChecked by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Toggle(checked = true, onCheckedChange = {})
        Toggle(checked = false, onCheckedChange = {})
        Toggle(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CounterPreview() {
    var count by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Counter(value = 1, onValueChange = {})
        Counter(value = 5, onValueChange = {})
        Counter(
            value = count,
            onValueChange = { count = it }
        )
    }
}