package com.project.taskmanagement.data.repository

import com.project.taskmanagement.common.ResultWrapper
import com.project.taskmanagement.database.AppDatabase
import com.project.taskmanagement.database.TaskEntity
import com.project.taskmanagement.domain.IGetTaskRepository
import com.project.taskmanagement.network.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import safeApiCall
import javax.inject.Inject

 class GetTaskRepository @Inject constructor(
    private val apiService: APIService,
    private val database: AppDatabase
) : IGetTaskRepository {

    private val taskDao = database.taskDao()

    override fun getTask(): Flow<List<TaskEntity>> = flow {
        val cachedTasks = taskDao.getAllTask().first()

        if (cachedTasks.isNotEmpty()) {
            emit(cachedTasks)
        } else {
            when (val result = refreshTask()) {
                is ResultWrapper.Success -> emit(result.value)
                is ResultWrapper.NetworkError -> emit(emptyList())
                is ResultWrapper.GenericError -> emit(emptyList())
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun refreshTask(): ResultWrapper<List<TaskEntity>> {
        return safeApiCall(Dispatchers.IO) {
            val apiResponse = apiService.getTodos()

            val taskEntities = apiResponse.map { task ->
                TaskEntity(
                    id = task.id,
                    title = task.title,
                    userId = task.userId,
                    completed = task.completed
                )
            }

            taskDao.insertTask(taskEntities)
            taskEntities
        }
    }

    override suspend fun insertTask(task: List<TaskEntity>) {
        taskDao.insertTask(task)
    }

    override suspend fun updateTask(task: TaskEntity) {
        taskDao.updateTask(task)
    }

     override suspend fun deleteTask(task: TaskEntity) {
         taskDao.deleteTask(task.id)
     }
 }
