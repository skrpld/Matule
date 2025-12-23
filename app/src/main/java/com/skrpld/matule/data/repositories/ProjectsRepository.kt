package com.skrpld.matule.data.repositories

import com.skrpld.matule.data.models.Project
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProjectsRepository() {

    // Имитация списка проектов
    private val _projects = MutableStateFlow<List<Project>>(
        listOf(
            Project(
                id = 1,
                title = "Мой первый проект",
                dateDisplay = "Прошло 2 дня"
            ),
            // Можно добавить еще для теста
            // Project(2, "Второй проект", "Вчера")
        )
    )
    val projects: StateFlow<List<Project>> = _projects.asStateFlow()

    suspend fun createProject(project: Project) {
        val currentList = _projects.value.toMutableList()
        currentList.add(0, project) // Добавляем в начало
        _projects.emit(currentList)
    }
}