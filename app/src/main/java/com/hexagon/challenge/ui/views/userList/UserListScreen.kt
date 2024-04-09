package com.hexagon.challenge.ui.views.userList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hexagon.challenge.HexagonApplication
import com.hexagon.challenge.R
import com.hexagon.challenge.ui.theme.BabyBlueDark
import com.hexagon.challenge.ui.theme.HexagonChallengeTheme
import com.hexagon.challenge.ui.views.components.HeaderTitle
import com.hexagon.challenge.utils.FormatData
import java.util.Locale

@Composable
fun UserListScreen(viewModel: UserListViewModel, onEditUserClick: (String) -> Unit) {
    val users by viewModel.users.observeAsState(initial = null)
    val defaultAvatar = painterResource(R.drawable.default_avatar)

    Surface(modifier = Modifier.fillMaxSize(), color = BabyBlueDark) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            HeaderTitle(title = "Cadastrar Usuário")
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
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        if (users!!.isEmpty()) {
                            Text(text = "Nenhum usuário cadastrado ainda.")
                        } else {
                            for (user in users!!) {
                                val bitmapImage: Painter =
                                    if (user.avatar != null && user.avatar.isNotEmpty()) {
                                        BitmapPainter(FormatData.byteArrayToImageBitmap(user.avatar))
                                    } else {
                                        defaultAvatar
                                    }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(15.dp))
                                        .background(Color.White)
                                        .padding(4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box {
                                        Image(
                                            painter = bitmapImage,
                                            contentDescription = "User Avatar",
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .width(50.dp)
                                                .height(50.dp)
                                                .clip(CircleShape)
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