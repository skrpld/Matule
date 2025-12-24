package com.skrpld.matule.ui.screens.projects

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skrpld.matule.data.repositories.ProjectsRepository
import com.skrpld.matule.ui.navigation.AppNavigation
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class ProjectsViewModel(
    private val projectsRepository: ProjectsRepository
) : ViewModel() {

    val projects = projectsRepository.projects.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun onAddProjectClick(navigation: AppNavigation) {
        navigation.navigateToCreateProject()
    }

    fun onOpenProject(projectId: Int) {
        Log.d("ProjectsViewModel", "Open project: $projectId")
    }
}