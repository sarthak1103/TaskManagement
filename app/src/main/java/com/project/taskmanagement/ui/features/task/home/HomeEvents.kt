package com.project.taskmanagement.ui.features.task.home

import com.project.taskmanagement.database.TaskEntity

sealed class HomeEvents {
    data class GetAllTask(val isFirstLoaded: Boolean) : HomeEvents()
    data class EditTask(val task: TaskEntity) : HomeEvents()
    data class AddTask(val task: TaskEntity) : HomeEvents()
    data class DeleteTask(val task: TaskEntity) : HomeEvents()
}