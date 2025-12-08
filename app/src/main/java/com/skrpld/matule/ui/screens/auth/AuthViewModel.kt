package com.skrpld.matule.ui.screens.auth

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.skrpld.matule.data.AuthState
import com.skrpld.matule.data.repositories.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authManager: AuthManager
) : ViewModel() {
    var currentAuthState: StateFlow<AuthState> = authManager.authState

    var firstNameInput by mutableStateOf("")
    var surNameInput by mutableStateOf("")
    var lastNameInput by mutableStateOf("")
    var birthDateInput by mutableStateOf("")
    var genderInput by mutableStateOf("")
    var emailInput by mutableStateOf("")
    var passwordInput by mutableStateOf("")
    var confirmPasswordInput by mutableStateOf("")
    var pinInput by mutableStateOf("")
    var confirmPinInput by mutableStateOf("")

    fun onLoginLock() {
        Log.d("AuthViewModel", "Pin input Done")
        authManager.loginLock(pinInput)
    }

    fun onLogin() {
        Log.d("AuthViewModel", "Start login")
        authManager.login(emailInput, passwordInput)
    }

    fun onSignupProfile() {
        Log.d("AuthViewModel", "Start signup profile")
        authManager.signupProfile(firstNameInput, surNameInput, lastNameInput, birthDateInput, genderInput, emailInput)
    }

    fun onSignupPassword() {
        Log.d("AuthViewModel", "Start signup password")
        authManager.signupPassword(passwordInput, confirmPasswordInput)
    }

    fun onSignupLock() {
        Log.d("AuthViewModel", "Start signup lock")
        authManager.signupLock(pinInput, confirmPinInput)
    }

    fun onLogout() {

    }
}