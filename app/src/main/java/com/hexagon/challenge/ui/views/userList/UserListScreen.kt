package com.hexagon.challenge.ui.views.userList

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.hexagon.challenge.ui.SharedViewModel
import com.hexagon.challenge.ui.theme.BabyBlueDark
import com.hexagon.challenge.ui.theme.HexagonChallengeTheme
import com.hexagon.challenge.ui.views.components.DeleteDialog
import com.hexagon.challenge.ui.views.components.HeaderTitle
import com.hexagon.challenge.utils.FormatData
import kotlinx.coroutines.launch
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserListScreen(
    viewModel: UserListViewModel,
    onEditUserClick: (String) -> Unit,
    sharedViewModel: SharedViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val users by viewModel.users.observeAsState(initial = null)
    val defaultAvatar = painterResource(R.drawable.default_avatar)
    val state = rememberScrollState()
    val openDeleteDialog = remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarMessage by sharedViewModel.snackbarMessage.collectAsState(coroutineScope.coroutineContext)

    if (snackbarMessage.show) {
        LaunchedEffect(snackbarMessage) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = snackbarMessage.message,
                    duration = SnackbarDuration.Short,
                    withDismissAction = false,
                )
            }
            sharedViewModel.showSnackBar(false, "")
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
            modifier = Modifier
                .fillMaxSize()
        ) {
            HeaderTitle(title = "Usuários cadastrados")
            if (users == null) {
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
                        .verticalScroll(state)
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
                                    .padding(bottom = 2.dp, top = 2.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .height(160.dp)
                                        .clip(RoundedCornerShape(15.dp))
                                        .background(Color.White),
                                    contentAlignment = Alignment.TopStart
                                ) {
                                    Image(
                                        painter = bitmapImage,
                                        contentDescription = "User Avatar",
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .width(90.dp)
                                            .height(90.dp)
                                            .clip(CircleShape)
                                            .border(2.dp, BabyBlueDark, CircleShape)
                                    )
                                    Column(
                                        modifier = Modifier.padding(8.dp),
                                    ) {
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
                                        Row(
                                            modifier = Modifier.padding(8.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Button(
                                                onClick = { onEditUserClick(user.id.toString()) }
                                            ) {
                                                Text(text = "Editar")
                                            }
                                            Spacer(modifier = Modifier.width(16.dp))
                                            Button(
                                                onClick = {
                                                    openDeleteDialog.value = true
                                                }
                                            ) {
                                                Text(text = "Excluir")
                                            }
                                            when {
                                                openDeleteDialog.value -> {
                                                    DeleteDialog(
                                                        onConfirmation = {
                                                            openDeleteDialog.value = false
                                                            coroutineScope.launch {
                                                                val deleteSuccessful = viewModel.deleteUser(user)
                                                                if (deleteSuccessful) {
                                                                    sharedViewModel.showSnackBar(
                                                                        true,
                                                                        "Usuário excluído com sucesso"
                                                                    )
                                                                } else {
                                                                    sharedViewModel.showSnackBar(
                                                                        true,
                                                                        "Erro ao excluir usuário"
                                                                    )
                                                                }
                                                            }
                                                        },
                                                        onDismissRequest = {
                                                            openDeleteDialog.value = false
                                                        },
                                                    )
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
}

@Preview(showBackground = true)
@Composable
fun UserListViewPreview() {
    HexagonChallengeTheme {
        UserListScreen(
            viewModel = UserListViewModel((HexagonApplication()).repository), onEditUserClick = {},
            sharedViewModel = SharedViewModel()
        )
    }
}