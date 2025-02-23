package com.project.taskmanagement.domain

import com.project.taskmanagement.common.ResultWrapper
import com.project.taskmanagement.database.TaskEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface IGetTaskRepository {
    fun getTask(): Flow<List<TaskEntity>>

    suspend fun refreshTask(): ResultWrapper<List<TaskEntity>>

    suspend fun insertTask(task: List<TaskEntity>)

    suspend fun updateTask(task: TaskEntity)

    suspend fun deleteTask(task: TaskEntity)
}
