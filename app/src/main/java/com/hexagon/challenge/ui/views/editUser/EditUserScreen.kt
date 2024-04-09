package com.hexagon.challenge.ui.views.editUser

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hexagon.challenge.ui.theme.BabyBlueDark
import com.hexagon.challenge.ui.theme.HexagonChallengeTheme
import com.hexagon.challenge.ui.views.components.ActiveField
import com.hexagon.challenge.ui.views.components.CpfTextField
import com.hexagon.challenge.ui.views.components.DateTextField
import com.hexagon.challenge.ui.views.components.HeaderTitle
import com.hexagon.challenge.ui.views.components.RegisterTextField
import com.hexagon.challenge.ui.views.editUser.EditUserViewModel
import kotlinx.coroutines.launch

@Composable
fun EditUserScreen(viewModel: EditUserViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val user by viewModel.userById.observeAsState(initial = null)
    val errorSaving by viewModel.errorSaving.collectAsState(coroutineScope.coroutineContext)
    val state = rememberScrollState()
    LaunchedEffect(Unit) { state.animateScrollTo(100) }

        Surface(modifier = Modifier.fillMaxSize(), color = Color.LightGray) {
            if (user == null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    HeaderTitle(title = "Editar Usuário")

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
                                    text = "Erro ao editar usuário: " + errorSaving.message + ". Tente novamente.",
                                    color = Color.Red
                                )
                            }
                        }

                        RegisterTextField(
                            title = "Nome",
                            placeholder = "",
                            textValue = user?.name ?: "",
                            onValueChange = viewModel::updateName
                        )
                        CpfTextField(
                            cpf = user?.cpf ?: "",
                            onValueChange = viewModel::updateCpf
                        )
                        DateTextField(
                            date = user?.birthDate ?: "",
                            onDateChange = viewModel::updateBirthDate
                        )
                        RegisterTextField(
                            title = "Cidade",
                            placeholder = "",
                            textValue = user?.city ?: "",
                            onValueChange = viewModel::updateCity
                        )
                        ActiveField(
                            active = user?.active ?: false,
                            onActiveChange = viewModel::updateActive
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = { coroutineScope.launch { viewModel.updateUser() } },
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
}