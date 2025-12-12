package com.skrpld.matule.ui.screens.auth

import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skrpld.matule.data.AuthState
import com.skrpld.matule.data.repositories.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authManager: AuthManager
) : ViewModel() {
    val currentAuthState: StateFlow<AuthState> = authManager.authState

    var firstNameInput by mutableStateOf("")
    var surNameInput by mutableStateOf("")
    var lastNameInput by mutableStateOf("")
    var birthDateInput by mutableStateOf("")
    var genderInput by mutableStateOf("")

    var emailInput by mutableStateOf("")
    var passwordInput by mutableStateOf("")
    var confirmPasswordInput by mutableStateOf("")
    var pinInput by mutableStateOf("")

    val isLoginValid by derivedStateOf {
        val emailPattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$")
        emailInput.matches(emailPattern) && passwordInput.length >= 6
    }

    val isProfileValid by derivedStateOf {
        val emailPattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$")
        firstNameInput.isNotBlank() &&
                surNameInput.isNotBlank() &&
                lastNameInput.isNotBlank() &&
                birthDateInput.isNotBlank() &&
                genderInput.isNotBlank() &&
                emailInput.matches(emailPattern)
    }

    val isPasswordValid by derivedStateOf {
        val passwordPattern = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$")
        passwordInput.matches(passwordPattern) && (passwordInput == confirmPasswordInput)
    }

    fun onLoginLock() {
        Log.d("AuthViewModel", "Login via Pin")
        viewModelScope.launch {
            try {
                authManager.loginLock(pinInput)
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Login Lock Error", e)
            }
        }
    }

    fun onLogin() {
        Log.d("AuthViewModel", "Start login")
        viewModelScope.launch {
            try {
                authManager.login(emailInput, passwordInput)
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Login Error", e)
            }
        }
    }

    fun onSignupComplete() {
        Log.d("AuthViewModel", "Completing signup with all data")
        viewModelScope.launch {
            try {
                authManager.signupComplete(
                    firstName = firstNameInput,
                    surName = surNameInput,
                    lastName = lastNameInput,
                    birthDate = birthDateInput,
                    gender = genderInput,
                    email = emailInput,
                    password = passwordInput,
                    pin = pinInput
                )
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Signup Error", e)
            }
        }
    }

    fun onLogout() {
        viewModelScope.launch {
            authManager.logout()
            clearFields()
        }
    }

    private fun clearFields() {
        firstNameInput = ""
        lastNameInput = ""
        surNameInput = ""
        birthDateInput = ""
        genderInput = ""
        emailInput = ""
        passwordInput = ""
        pinInput = ""
    }
}