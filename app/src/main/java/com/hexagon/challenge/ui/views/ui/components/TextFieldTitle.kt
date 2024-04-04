package com.hexagon.challenge.ui.views.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextFieldTitle(fieldTitle: String) {
    Row (
        modifier = Modifier
            .background(Color.Transparent)
    ) {
        Text(
            text = fieldTitle,
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun TextFieldTitlePreview() {
    TextFieldTitle("Nome")
}