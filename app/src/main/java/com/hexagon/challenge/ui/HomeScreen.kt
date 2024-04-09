package com.hexagon.challenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hexagon.challenge.ui.theme.HexagonChallengeTheme
import java.io.File

@Composable
fun HomeScreen(onRegisterClick: () -> Unit, onUserListClick: () -> Unit) {
    // add buttons for navigation
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Hello, world!")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter =
                contentDescription = "Image showing people in a corporate setting"
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onRegisterClick,
                modifier = Modifier.padding(8.dp, 4.dp)
            ) {
                Text("Inserir Novo")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onUserListClick,
                modifier = Modifier.padding(8.dp, 4.dp)
            ) {
                Text("Ver Usuários")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HexagonChallengeTheme {
        HomeScreen({}, {})
    }
}