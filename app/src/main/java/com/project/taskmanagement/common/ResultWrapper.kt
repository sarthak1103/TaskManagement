package com.project.taskmanagement.common

sealed class ResultWrapper<out T> {
    data class Success<T>(val value: T) : ResultWrapper<T>()
    data class NetworkError(val errorMessage: String = "Network error") : ResultWrapper<Nothing>()
    data class GenericError(val errorMessage: String? = null) : ResultWrapper<Nothing>()
}
