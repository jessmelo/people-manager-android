package com.hexagon.challenge

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hexagon.challenge.ui.views.editUser.EditUserScreen
import com.hexagon.challenge.ui.views.editUser.EditUserViewModel
import com.hexagon.challenge.ui.views.register.RegisterScreen
import com.hexagon.challenge.ui.views.register.RegisterViewModel
import com.hexagon.challenge.ui.views.userList.UserListScreen
import com.hexagon.challenge.ui.views.userList.UserListViewModel

@Composable
fun AppNavigation(
    application: HexagonApplication,
) {
    val navController = rememberNavController()
    val userRepository = application.repository

    NavHost(navController, startDestination = "home") {
        composable("home") { HomePage(
            onRegisterClick = { navController.navigate("register") },
            onUserListClick = { navController.navigate("userList") }
        ) }
        composable("register") { RegisterScreen(RegisterViewModel(userRepository)) }
        composable("userList") { UserListScreen(UserListViewModel(userRepository),
            onEditUserClick = { userID: String -> navController.navigate("editUser/$userID") }
        ) }
        composable("editUser/{userID}",
            arguments = listOf(navArgument("userID") { type = NavType.StringType })
        ){ EditUserScreen(
            EditUserViewModel(userRepository, it.arguments?.getString("userID") ?: ""))
        }
    }
}