package com.project.taskmanagement.ui.features.task.home

import com.project.taskmanagement.data.response.GetTaskResponseItem
import com.project.taskmanagement.database.TaskEntity

data class HomeState(
    val isLoading: Boolean = false,
    val data: List<TaskEntity> = emptyList(),
    val error: String = "",
    val success: String = ""
)