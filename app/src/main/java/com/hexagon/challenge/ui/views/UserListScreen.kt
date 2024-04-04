package com.hexagon.challenge.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.hexagon.challenge.HexagonApplication
import com.hexagon.challenge.ui.theme.HexagonChallengeTheme
import com.hexagon.challenge.ui.views.components.HeaderTitle
import com.hexagon.challenge.ui.views.userList.UserListViewModel

@Composable
fun UserListScreen(viewModel: UserListViewModel) {
    HexagonChallengeTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.LightGray) {
            Column {
                HeaderTitle(title = "Usuários cadastrados")
                if (viewModel.users.value?.isEmpty() == true) {
                    Text(text = "Nenhum usuário cadastrado")
                } else {
                    for (user in viewModel.users.value!!.iterator()) {
                        Row {
                            Text(text = user.name)
                            Text(text = user.cpf)
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
            viewModel = UserListViewModel((HexagonApplication()).repository)
        )
    }
}