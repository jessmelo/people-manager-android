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
import com.hexagon.challenge.ui.theme.BabyBlueDark
import com.hexagon.challenge.ui.theme.HexagonChallengeTheme
import com.hexagon.challenge.ui.views.ui.components.HeaderTitle
import com.hexagon.challenge.ui.views.ui.components.RegisterDatePicker
import com.hexagon.challenge.ui.views.ui.components.RegisterTextField
import com.hexagon.challenge.ui.views.ui.register.RegisterViewModel

@Composable
fun RegisterView(viewModel: RegisterViewModel) {
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
        RegisterView(RegisterViewModel())
    }
}