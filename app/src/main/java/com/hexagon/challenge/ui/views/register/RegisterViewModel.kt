package com.hexagon.challenge.ui.views.register

import androidx.lifecycle.ViewModel
import com.hexagon.challenge.data.model.User
import com.hexagon.challenge.data.repository.UserRepository

class RegisterViewModel(
    private val repository: UserRepository
) : ViewModel() {
    private var errorMessage: String = ""
    var userModel: User = User(0, "", "", "", "", "", false)

    private var errorAction: (() -> Unit)? = {
        if (userModel.cpf.length != 11) {
            // show error message
            errorMessage = "CPF deve conter 11 dígitos"
        } else if (
            userModel.name.isEmpty() ||
            userModel.birthDate.isEmpty() ||
            userModel.cpf.isEmpty() ||
            userModel.city.isEmpty() ||
            userModel.avatar.isEmpty()
        ) {
            errorMessage = "Preencha todos os campos obrigatórios"
        }
    }
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
        if (userModel.name.isNotEmpty() && userModel.birthDate.isNotEmpty() && userModel.cpf.isNotEmpty() && userModel.city.isNotEmpty() && userModel.avatar.isNotEmpty()) {
            if (userModel.cpf.length != 11) {
                errorAction?.invoke()
                return
            }
            repository.insert(userModel)
            // clean user model
            userModel = User(0, "", "", "", "", "", false)
            // go to main screen

        }
    }
}