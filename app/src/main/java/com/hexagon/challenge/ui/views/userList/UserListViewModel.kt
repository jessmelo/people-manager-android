package com.hexagon.challenge.ui.views.userList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hexagon.challenge.data.model.User
import com.hexagon.challenge.data.repository.UserRepository

class UserListViewModel(private val userRepository: UserRepository): ViewModel() {
    val users = userRepository.getAll().asLiveData()

    suspend fun deleteUser(user: User): Boolean {
        return userRepository.delete(user)
    }
}