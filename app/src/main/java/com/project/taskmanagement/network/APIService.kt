package com.project.taskmanagement.network
import com.project.taskmanagement.data.response.GetTaskResponseItem
import retrofit2.http.GET

interface APIService {
    @GET("todos")
    suspend fun getTodos(): List<GetTaskResponseItem>
}