package com.hexagon.challenge.ui.views.editUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hexagon.challenge.data.model.ErrorSaving
import com.hexagon.challenge.data.model.User
import com.hexagon.challenge.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditUserViewModel(
    private val repository: UserRepository,
    userID: String
): ViewModel() {
    var userById: LiveData<User> = repository.getById(userID.toInt()).asLiveData()
    private val _errorSaving = MutableStateFlow(ErrorSaving(error = false, message = ""))
    val errorSaving = _errorSaving.asStateFlow()

    private var userModel = User(
        id = userID.toInt(),
        name = "",
        birthDate = "",
        cpf = "",
        city = "",
        avatar = "",
        active = false
    )

    fun loadUser() {
        userById.value?.let {
            userModel = it
        }
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

    private fun updateErrorSaving(newError: ErrorSaving) {
        _errorSaving.value = newError
    }

    suspend fun updateUser() {
        if (userModel.name.isNotEmpty() && userModel.birthDate.isNotEmpty() &&
            userModel.cpf.isNotEmpty() && userModel.city.isNotEmpty()) {
            val cpf = userModel.cpf.filter { it.isDigit() }
            val birthDate = userModel.birthDate.filter { it.isDigit() }
            if (cpf.length < 11) {
                updateErrorSaving(ErrorSaving(error = true, message = "CPF inválido"))
                return
            }
            if (birthDate.length < 8) {
                updateErrorSaving(ErrorSaving(error = true, message = "Data de nascimento inválida"))
                return
            }
            formatData(cpf, birthDate)

            val userUpdate = repository.update(userModel)
            if (userUpdate != 0) {
                // show success message
            } else {
                updateErrorSaving(ErrorSaving(error = true, message = "Erro ao atualizar usuário"))
            }
        }
    }
    private fun formatData(cpf: String, birthDate: String) {
        val editedCpf = "${cpf.substring(0, 3)}.${cpf.substring(3, 6)}.${cpf.substring(6, 9)}-${cpf.substring(9, 11)}"
        val editedBirthData = "${birthDate.substring(0, 2)}/${birthDate.substring(2, 4)}/${birthDate.substring(4, 8)}"
        userModel = userModel.copy(cpf = editedCpf, birthDate = editedBirthData)
    }

    init {
        userById.observeForever { user ->
            userModel = user
        }
    }
}
