package com.hexagon.challenge.ui.views.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ActiveField(
    active: Boolean,
    onActiveChange: (Boolean) -> Unit = {}
) {
    var switchValue by remember { mutableStateOf(active) }
    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = "Ativo",
        )
        Switch(
            checked = switchValue,
            onCheckedChange = { value: Boolean->
                switchValue = value
                onActiveChange(value)
            },
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp, 4.dp)
        )
    }
}

@Preview
@Composable
fun ActiveFieldPreview() {
    ActiveField(true)
}