package com.hexagon.challenge.ui.views.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RegisterTextField(title: String, placeholder: String, textValue: String, onValueChange: (String) -> Unit = {}) {
    var text by remember { mutableStateOf(textValue) }

    Row (
        modifier = Modifier.fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = title,
        )
    }
    Row (
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField (
            value = text,
            onValueChange = { it ->
                text = it
                onValueChange(it)
            },
            placeholder = { Text(placeholder) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun RegisterTextFieldPreview() {
    RegisterTextField("Nome", "Ana Maria", "Ana Maria")
}