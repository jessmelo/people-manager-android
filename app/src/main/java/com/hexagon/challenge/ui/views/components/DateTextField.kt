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
import kotlin.math.min

@Composable
fun DateTextField(date: String, onDateChange: (String) -> Unit = {}) {
    var text by remember { mutableStateOf(date) }
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
            onValueChange = { value ->
                if (value.length <= 10) {
                    text = value
                    onDateChange(value)
                } },
            placeholder = { Text("01/01/1990") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = DateVisualTransformation()
        )
    }
}

@Preview
@Composable
fun RegisterDatePickerPreview() {
    DateTextField("01/01/2022")
}

class DateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        var transformedText = ""
        val digits = text.text.filter { it.isDigit() }
        val trimmed = if (digits.length >= 8) digits.substring(0..7) else digits
        for (i in trimmed.indices) {
            transformedText += trimmed[i]
            if (i == 1 || i == 3) transformedText += "/"
        }

        val dateOffsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 2) return min(offset, transformedText.length)
                if (offset <= 5) return min(offset + 1, transformedText.length)
                if (offset <= 10) return min(offset + 2, transformedText.length)
                return transformedText.length
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 2) return offset
                if (offset <= 5) return offset - 1
                return offset - 2
            }
        }
        return TransformedText(AnnotatedString(transformedText), dateOffsetMapping)
    }
}