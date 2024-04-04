package com.hexagon.challenge;

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hexagon.challenge.ui.views.RegisterView
import com.hexagon.challenge.ui.views.UserListView
import com.hexagon.challenge.ui.views.ui.register.RegisterViewModel
import com.hexagon.challenge.ui.views.ui.userList.UserListViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home") { HomePage() }
        composable("register") { RegisterView(RegisterViewModel()) }
        composable("userList") { UserListView(UserListViewModel((HexagonApplication()).repository)) }
    }
}