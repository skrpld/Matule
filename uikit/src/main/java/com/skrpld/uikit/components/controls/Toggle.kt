package com.skrpld.uikit.components.controls

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skrpld.uikit.theme.Accent
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

@Preview(showBackground = true)
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