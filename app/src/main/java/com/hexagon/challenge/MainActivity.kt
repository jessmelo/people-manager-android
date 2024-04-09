package com.hexagon.challenge

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hexagon.challenge.ui.SharedViewModel
import com.hexagon.challenge.ui.theme.HexagonChallengeTheme

class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pickImageLauncherRegister = registerForActivityResult(
            ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                sharedViewModel.updatePickedImageUri(uri.toString())
            } else {
                Log.d("MainActivity", "Image URI is null")
            }
        }

        setContent {
            HexagonChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNavigation(application as HexagonApplication, pickImageLauncherRegister)
                }
            }
        }
    }
}

@Composable
fun HomePage(onRegisterClick: () -> Unit, onUserListClick: () -> Unit) {
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
            Button(
                onClick = onRegisterClick,
                modifier = Modifier.padding(8.dp, 4.dp)
            ) {
                Text("Register")
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