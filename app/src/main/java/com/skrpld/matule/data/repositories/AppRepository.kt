package com.skrpld.matule.data.repositories

import com.skrpld.matule.data.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class AppRepository @Inject constructor() {
    private val _firstName = MutableStateFlow<String>("")
    val firstName: StateFlow<String> = _firstName.asStateFlow()

    private val _email = MutableStateFlow<AuthState>(AuthState.LoggedOut)
    val email: StateFlow<AuthState> = _email.asStateFlow()

    fun logout() {
        // TODO: Выход из аккаунта
    }
}