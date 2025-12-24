package com.skrpld.matule

import android.app.Application
import com.skrpld.matule.data.repositories.AppRepository
import com.skrpld.matule.data.repositories.AuthManager
import com.skrpld.matule.data.repositories.MainRepository
import com.skrpld.matule.data.repositories.ProjectsRepository
import com.skrpld.matule.ui.screens.auth.AuthViewModel
import com.skrpld.matule.ui.screens.home.HomeViewModel
import com.skrpld.matule.ui.screens.profile.ProfileViewModel
import com.skrpld.matule.ui.screens.projects.ProjectsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class MatuleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MatuleApplication)
            modules(appModule)
        }
    }
}

val appModule = module {
    single { AppRepository() }
    single { AuthManager() }
    single { MainRepository() }
    single { ProjectsRepository() }

    viewModel { AuthViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { ProjectsViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}