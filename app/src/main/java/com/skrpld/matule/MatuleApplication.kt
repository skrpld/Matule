package com.skrpld.matule

import org.koin.dsl.module
import android.app.Application
import com.skrpld.matule.data.repositories.AppRepository
import com.skrpld.matule.data.repositories.AuthManager
import com.skrpld.matule.data.repositories.MainRepository
import com.skrpld.matule.data.repositories.ProjectsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.dsl.viewModel
import com.skrpld.matule.ui.screens.auth.AuthViewModel

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
}