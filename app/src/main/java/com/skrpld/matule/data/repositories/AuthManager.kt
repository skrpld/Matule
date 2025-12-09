package com.skrpld.matule.data.repositories

import com.skrpld.matule.data.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class AuthManager @Inject constructor() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.LoggedOut)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    fun loginLock(pin: String) {
        //TODO: Хешироввание
    }

    fun login(
        email: String,
        password: String
    ) {
        //TODO: Хешировать пароль
    }

    fun registerFullUser(
        firstName: String,
        surName: String,
        lastName: String,
        birthDate: String,
        gender: String,
        email: String,
        password: String,
        pin : String
    ) {
        //TODO: Зарегать пользователя
    }


    fun logout() {

    }
}