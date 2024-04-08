package com.hexagon.challenge.ui.views.userList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hexagon.challenge.HexagonApplication
import com.hexagon.challenge.R
import com.hexagon.challenge.ui.theme.BabyBlueDark
import com.hexagon.challenge.ui.theme.HexagonChallengeTheme
import com.hexagon.challenge.ui.views.components.HeaderTitle
import java.util.Locale

@Composable
fun UserListScreen(viewModel: UserListViewModel, onEditUserClick: (String) -> Unit) {
    val users by viewModel.users.observeAsState(initial = null)

    HexagonChallengeTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = BabyBlueDark) {
            if (users == null) {
                // Display loading object
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BabyBlueDark)
                        .padding(16.dp)
                ) {
                    Text(text = "Carregando usuários...")
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BabyBlueDark)
                        .padding(16.dp)
                ) {
                    HeaderTitle(title = "Usuários cadastrados")
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        if (users!!.isEmpty()) {
                            Text(text = "Nenhum usuário cadastrado ainda.")
                        } else {
                            for (user in users!!) {
                                Box {
                                    Image(
                                        painter = painterResource(id = R.drawable.default_avatar),
                                        contentDescription = "Avatar",
                                        modifier = Modifier
                                            .width(50.dp)
                                            .height(50.dp)
                                            .clip(RoundedCornerShape(15.dp))
                                    )
                                    Column {
                                        Text(
                                            text = user.name.uppercase(Locale.getDefault()),
                                            minLines = 1
                                        )
                                        Text(text = "CPF: ${user.cpf}", minLines = 1)
                                        Text(
                                            text = "Data de Nascimento: ${user.birthDate}",
                                            minLines = 1
                                        )
                                        Text(text = "Cidade: ${user.city}", minLines = 1)
                                    }
                                    Button(
                                        onClick = { onEditUserClick(user.id.toString()) },
                                        modifier = Modifier.padding(8.dp)
                                    ) {
                                        Text(text = "Editar")
                                    }
                                    Button(
                                        onClick = { viewModel.deleteUser(user) },
                                        modifier = Modifier.padding(8.dp)
                                    ) {
                                        Text(text = "Excluir")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserListViewPreview() {
    HexagonChallengeTheme {
        UserListScreen(
            viewModel = UserListViewModel((HexagonApplication()).repository), onEditUserClick = {}
        )
    }
}