package com.skrpld.matule.ui.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.skrpld.matule.data.repositories.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {
    var firstName by mutableStateOf(appRepository.firstName)
    var email by mutableStateOf(appRepository.email)

    var showNotifications by mutableStateOf(true)

    fun toggleNotifications() {
        showNotifications = !showNotifications
    }

    fun logout() {
        appRepository.logout()
    }
}