package com.hexagon.challenge.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hexagon.challenge.HexagonApplication
import com.hexagon.challenge.ui.theme.BabyBlueDark
import com.hexagon.challenge.ui.theme.HexagonChallengeTheme
import com.hexagon.challenge.ui.views.components.ActiveField
import com.hexagon.challenge.ui.views.components.HeaderTitle
import com.hexagon.challenge.ui.views.components.RegisterDatePicker
import com.hexagon.challenge.ui.views.components.RegisterTextField
import com.hexagon.challenge.ui.views.register.RegisterViewModel

@Composable
fun RegisterScreen(viewModel: RegisterViewModel) {
    // show error message if there is any missing field after clicking save

    HexagonChallengeTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = Color.LightGray) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BabyBlueDark)
                    .padding(16.dp)
            ){

                HeaderTitle(title = "Cadastro de Usuário")
                RegisterTextField(
                    title = "Nome",
                    placeholder = "Ana Maria",
                    textValue = viewModel.userModel.name,
                    onValueChange = viewModel::updateName
                )
                RegisterTextField(
                    title = "CPF",
                    placeholder = "000.000.000-00",
                    textValue = viewModel.userModel.cpf,
                    onValueChange = viewModel::updateCpf
                )
                RegisterDatePicker(
                    date = viewModel.userModel.birthDate,
                    onDateChange = viewModel::updateBirthDate
                )
                RegisterTextField(
                    title = "Cidade",
                    placeholder = "São Paulo",
                    textValue = viewModel.userModel.city,
                    onValueChange = viewModel::updateCity
                )
                ActiveField(
                    active = viewModel.userModel.active,
                    onActiveChange = viewModel::updateActive
                )
                Row (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Button(
                        onClick = viewModel::registerUser,
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
    HexagonChallengeTheme {
        RegisterScreen(
            RegisterViewModel(
                HexagonApplication().repository
            )
        )
    }
}