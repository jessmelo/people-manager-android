package com.hexagon.challenge.ui.views.register

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hexagon.challenge.ui.SharedViewModel
import com.hexagon.challenge.ui.theme.BabyBlueDark
import com.hexagon.challenge.ui.views.components.ActiveField
import com.hexagon.challenge.ui.views.components.AvatarImageField
import com.hexagon.challenge.ui.views.components.CpfTextField
import com.hexagon.challenge.ui.views.components.DateTextField
import com.hexagon.challenge.ui.views.components.HeaderTitle
import com.hexagon.challenge.ui.views.components.RegisterTextField
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    sharedViewModel: SharedViewModel,
    pickImageLauncherRegister: ActivityResultLauncher<String>,
    onUserCreated: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val errorSaving by viewModel.errorSaving.collectAsState(coroutineScope.coroutineContext)
    val state = rememberScrollState()
    val pickedImageUri by sharedViewModel.pickedImageUri.collectAsState(coroutineScope.coroutineContext)

    if (pickedImageUri != null) {
        val context: Context = LocalContext.current.applicationContext
        viewModel.updateAvatar(pickedImageUri!!, context)
        sharedViewModel.updatePickedImageUri(null)
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Color.LightGray) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            HeaderTitle(title = "Cadastrar Usuário")

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BabyBlueDark)
                    .padding(16.dp)
                    .verticalScroll(state),
            ) {
                if (errorSaving.error) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(top = 4.dp),
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            text = errorSaving.message,
                            color = Color.Red
                        )
                    }
                }
                AvatarImageField(
                    image = viewModel.userModel.avatar ?: ByteArray(0),
                    galleryLauncher = {
                        pickImageLauncherRegister.launch("image/*")
                    }
                )
                RegisterTextField(
                    title = "Nome",
                    placeholder = "Ana Maria",
                    textValue = viewModel.userModel.name,
                    onValueChange = viewModel::updateName
                )
                CpfTextField(
                    cpf = viewModel.userModel.cpf,
                    onValueChange = viewModel::updateCpf
                )
                DateTextField(
                    date = viewModel.userModel.birthDate,
                    onDateChange = viewModel::updateBirthDate
                )
                RegisterTextField(
                    title = "Cidade",
                    placeholder = "Ex.: São Paulo - SP",
                    textValue = viewModel.userModel.city,
                    onValueChange = viewModel::updateCity
                )
                ActiveField(
                    active = viewModel.userModel.active,
                    onActiveChange = viewModel::updateActive
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { coroutineScope.launch {
                            viewModel.registerUser()
                            if (!errorSaving.error) {
                                onUserCreated()
                            }
                        } },
                        modifier = Modifier
                            .padding(16.dp)
                            .height(50.dp)
                    ) {
                        Text("Salvar")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterViewPreview() {
/*
    HexagonChallengeTheme {
        RegisterScreen(
            RegisterViewModel(
                HexagonApplication().repository,
            ),
            SharedViewModel(),

        )
    }
*/
}