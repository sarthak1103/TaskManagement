package com.project.taskmanagement.data.response


import com.google.gson.annotations.SerializedName

data class GetTaskResponseItem(
    @SerializedName("completed")
    val completed: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)