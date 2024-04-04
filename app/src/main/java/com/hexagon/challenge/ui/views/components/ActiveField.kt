package com.hexagon.challenge.ui.views.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ActiveField(
    active: Boolean,
    onActiveChange: (Boolean) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Ativo",
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Switch(
            checked = active,
            onCheckedChange = { onActiveChange(it) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun ActiveFieldPreview() {
    ActiveField(true)
}