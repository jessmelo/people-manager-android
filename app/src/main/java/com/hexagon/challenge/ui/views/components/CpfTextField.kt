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
fun CpfTextField(
    cpf: String,
    onValueChange: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf(cpf) }

    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = "CPF",
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = text,
            onValueChange = { value: String ->
                if (value.length <= 14) {
                    text = value
                    onValueChange(value)
                }
            },
            placeholder = { Text("123.456.789-00") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = CpfVisualTransformation()
        )
    }
}

@Preview
@Composable
fun CpfTextFieldPreview() {
    CpfTextField("123.456.789-00")
}

class CpfVisualTransformation: VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // exclude all characters that are not digits
        var transformedText = ""
        val digits = text.text.filter { it.isDigit() }
        val trimmed = if (digits.length >= 11) digits.substring(0..10) else digits
        for (i in trimmed.indices) {
            transformedText += trimmed[i]
            if (i == 2 || i == 5) {
                transformedText += "."
            } else if (i == 8) {
                transformedText += "-"
            }
        }

        val cpfOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 3) return min(offset, transformedText.length)
                if (offset <= 6) return min(offset + 1, transformedText.length)
                if (offset <= 9) return min(offset + 2, transformedText.length)
                if (offset <= 11) return min(offset + 3, transformedText.length)
                return transformedText.length
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 3) return offset
                if (offset <= 6) return offset - 1
                if (offset <= 9) return offset - 2
                if (offset <= 11) return offset - 3
                return 11
            }
        }
        return TransformedText(AnnotatedString(transformedText), cpfOffsetTranslator)
    }
}