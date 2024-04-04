package com.hexagon.challenge.ui.views.ui.register

import androidx.lifecycle.ViewModel
import com.hexagon.challenge.data.model.User

class RegisterViewModel : ViewModel() {
    var userModel: User = User(0, "", "", "", "", "", false)

    fun updateId(id: Int) {
        userModel = userModel.copy(id = id)
    }

    fun updateName(name: String) {
        userModel = userModel.copy(name = name)
    }

    fun updateBirthDate(birthDate: String) {
        userModel = userModel.copy(birthDate = birthDate)
    }

    fun updateCpf(cpf: String) {
        userModel = userModel.copy(cpf = cpf)
    }

    fun updateCity(city: String) {
        userModel = userModel.copy(city = city)
    }

    fun updateAvatar(avatar: String) {
        userModel = userModel.copy(avatar = avatar)
    }

    fun updateActive(active: Boolean) {
        userModel = userModel.copy(active = active)
    }

    fun registerUser() {
    }
}