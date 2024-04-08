package com.hexagon.challenge.ui.views.userList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hexagon.challenge.data.model.User
import com.hexagon.challenge.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserListViewModel(private val userRepository: UserRepository): ViewModel() {
    val users = userRepository.getAll().asLiveData()

    fun showUsers() {
        userRepository.getAll()
    }

    fun deleteUser(user: User) {
        userRepository.delete(user)
    }
}