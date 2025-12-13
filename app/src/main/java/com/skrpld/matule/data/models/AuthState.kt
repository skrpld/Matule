package com.skrpld.matule.data.models

sealed class AuthState {
    data object LoggedIn : AuthState()
    data object LoggedOut : AuthState()
    data object Registering : AuthState()
    data object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}