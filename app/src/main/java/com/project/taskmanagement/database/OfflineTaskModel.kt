package com.project.taskmanagement.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // ✅ Non-nullable, Room will auto-generate
    val title: String, // ✅ Non-nullable, no default needed
    val userId: Int = 1, // ✅ Non-nullable, default set to `1`
    val completed: Boolean = false // ✅ Non-nullable, default `false`
)