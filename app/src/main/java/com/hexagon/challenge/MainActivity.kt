package com.hexagon.challenge

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.hexagon.challenge.ui.SharedViewModel
import com.hexagon.challenge.ui.theme.HexagonChallengeTheme

class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by lazy {
        (application as HexagonApplication).sharedViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pickImageLauncherRegister = registerForActivityResult(
            ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                Log.d("MainActivity", "Image URI: $uri")
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
