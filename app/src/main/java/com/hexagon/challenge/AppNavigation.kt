package com.hexagon.challenge;

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hexagon.challenge.ui.views.EditUserScreen
import com.hexagon.challenge.ui.views.RegisterScreen
import com.hexagon.challenge.ui.views.UserListScreen
import com.hexagon.challenge.ui.views.editUser.EditUserViewModel
import com.hexagon.challenge.ui.views.register.RegisterViewModel
import com.hexagon.challenge.ui.views.userList.UserListViewModel

@Composable
fun AppNavigation(
    application: HexagonApplication
) {
    val navController = rememberNavController()
    val userRepository = application.repository

    NavHost(navController, startDestination = "home") {
        composable("home") { HomePage(
            onRegisterClick = { navController.navigate("register") },
            onUserListClick = { navController.navigate("userList") }
        ) }
        composable("register") { RegisterScreen(
            RegisterViewModel(userRepository)) }
        composable("userList") { UserListScreen(
            UserListViewModel(userRepository)) }
        composable("editUser") { EditUserScreen(
            EditUserViewModel(userRepository)) }
    }
}