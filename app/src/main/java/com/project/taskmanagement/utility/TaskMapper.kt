package com.project.taskmanagement.utility

import com.project.taskmanagement.data.response.GetTaskResponseItem
import com.project.taskmanagement.database.TaskEntity

fun TaskEntity.toGetTaskResponseItem(): GetTaskResponseItem {
    return GetTaskResponseItem(
        id = this.id,
        title = this.title,
        userId = this.userId,
        completed = this.completed
    )
}