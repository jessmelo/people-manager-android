package com.hexagon.challenge.ui.views.components

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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RegisterDatePicker(date: String, onDateChange: (String) -> Unit = {}) {
    var text by remember { mutableStateOf(date) }
    // birth date picker
    Row (
        modifier = Modifier.fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = "Data de Nascimento",
        )
    }
    Row (
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = text,
            onValueChange = { it ->
                text = it
                onDateChange(it) },
            placeholder = { Text("01/01/1990") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = DateVisualTransformation()
        )
    }
}

@Preview
@Composable
fun RegisterDatePickerPreview() {
    RegisterDatePicker("01/01/2022")
}

class DateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 10) text.text.substring(0..9) else text.text
        val filled = StringBuilder(trimmed).apply {
            while (length < 10) append(' ')
        }
        for (i in filled.indices) {
            when (i) {
                2, 5 -> if (filled[i] != '/') filled.insert(i, '/')
            }
        }
        val newText = filled.trimEnd().toString()
        return TransformedText(AnnotatedString(newText), OffsetMapping.Identity)
    }
}