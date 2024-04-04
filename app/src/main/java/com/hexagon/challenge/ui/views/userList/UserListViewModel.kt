package com.hexagon.challenge.ui.views.userList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.hexagon.challenge.data.model.User
import com.hexagon.challenge.data.repository.UserRepository

class UserListViewModel(private val userRepository: UserRepository): ViewModel() {
    val users: LiveData<List<User>> = userRepository.getAll().asLiveData()
    fun showUsers() {
        userRepository.getAll()
    }
}

class UserListViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}