package com.hexagon.challenge.ui.views.register

import android.util.Log
import android.util.Log.INFO
import androidx.lifecycle.ViewModel
import com.hexagon.challenge.data.model.ErrorSaving
import com.hexagon.challenge.data.model.User
import com.hexagon.challenge.data.repository.UserRepository
import com.hexagon.challenge.utils.FormatData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel(
    private val repository: UserRepository
) : ViewModel() {
    private val _errorSaving = MutableStateFlow(ErrorSaving(error = false, message = ""))
    val errorSaving = _errorSaving.asStateFlow()

    var userModel = User(
        name = "",
        birthDate = "",
        cpf = "",
        city = "",
        active = false
    )

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

    suspend fun registerUser() {
        if (userModel.name.isEmpty()) {
            updateErrorSaving(ErrorSaving(error = true, message = "Erro ao registrar usuário: Nome não pode ser vazio"))
            return
        }
        if (userModel.birthDate.isEmpty()) {
            updateErrorSaving(ErrorSaving(error = true, message = "Erro ao registrar usuário: Data de nascimento não pode ser vazia"))
            return
        }
        if (userModel.cpf.isEmpty()) {
            updateErrorSaving(ErrorSaving(error = true, message = "Erro ao registrar usuário: CPF não pode ser vazio"))
            return
        }
        if (userModel.city.isEmpty()) {
            updateErrorSaving(ErrorSaving(error = true, message = "Erro ao registrar usuário: Cidade não pode ser vazia"))
            return
        }
        if (userModel.name.isNotEmpty() && userModel.birthDate.isNotEmpty() &&
            userModel.cpf.isNotEmpty() && userModel.city.isNotEmpty()) {

            val cpf = userModel.cpf.filter { it.isDigit() }
            if (cpf.length < 11) {
                updateErrorSaving(ErrorSaving(error = true, message = "CPF inválido"))
                return
            } else {
                userModel = userModel.copy(cpf = FormatData.formatCPF(cpf))
            }

            val birthDate = userModel.birthDate.filter { it.isDigit() }
            if (birthDate.length < 8) {
                updateErrorSaving(ErrorSaving(error = true, message = "Data de nascimento inválida"))
                return
            } else {
                userModel = userModel.copy(birthDate = FormatData.formatBirthDate(birthDate))
            }

            val newUserID = repository.insert(userModel)

            if (newUserID > 0) {
                Log.println(INFO, "RegisterViewModel", "User registered successfully with id: $newUserID")
                userModel = User(
                    name = "",
                    birthDate = "",
                    cpf = "",
                    city = "",
                    active = false
                )
            } else {
                updateErrorSaving(ErrorSaving(error = true, message = "Erro ao cadastrar usuário. " +
                        "Tente novamente mais tarde."))
            }

            // clean user model
            userModel = User(
                name = "",
                birthDate = "",
                cpf = "",
                city = "",
                active = false
            )
            // go to main screen
        }
    }

    fun pickImage() {
        TODO("Not yet implemented")
    }
}