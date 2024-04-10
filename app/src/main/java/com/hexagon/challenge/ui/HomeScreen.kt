package com.hexagon.challenge.ui

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hexagon.challenge.ui.theme.HexagonChallengeTheme
import kotlinx.coroutines.launch
import java.io.InputStream

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: SharedViewModel,
    onRegisterClick: () -> Unit, onUserListClick: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarMessage by viewModel.snackbarMessage.collectAsState(coroutineScope.coroutineContext)

    if (snackbarMessage.show) {
        LaunchedEffect(snackbarMessage) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = snackbarMessage.message,
                    duration = SnackbarDuration.Short,
                    withDismissAction = false,
                )
            }
            viewModel.showSnackBar(false, "")
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(16.dp)
                    .fillMaxWidth(),
            )
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "People Manager HR")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                val context = LocalContext.current
                val assetManager = context.assets
                val inputStream: InputStream = assetManager.open("images/home_screen.png")
                val bitmap = remember {
                    BitmapFactory.decodeStream(inputStream)
                }
                Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Hexagon Logo")
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
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HexagonChallengeTheme {
        // HomeScreen({}, {})
    }
}