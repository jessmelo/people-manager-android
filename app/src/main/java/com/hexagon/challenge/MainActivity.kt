package com.hexagon.challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hexagon.challenge.ui.theme.HexagonChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HexagonChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNavigation(application as HexagonApplication)
                }
            }
        }
    }
}

@Composable
fun HomePage(onRegisterClick: () -> Unit, onUserListClick: () -> Unit) {
    // add buttons for navigation
    Column() {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Hello, world!")
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = onRegisterClick) {
                Text("Register")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = onUserListClick) {
                Text("User List")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HexagonChallengeTheme {
        HomePage({}, {})
    }
}