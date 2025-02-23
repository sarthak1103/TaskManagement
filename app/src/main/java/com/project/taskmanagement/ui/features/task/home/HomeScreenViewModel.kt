package com.project.taskmanagement.ui.features.task.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.animeapp.data.repository.GetTaskRepository
import com.project.taskmanagement.database.TaskEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getTaskRepository: GetTaskRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state
    fun onEvent(event: HomeEvents) {
        when (event) {
            is HomeEvents.GetAllTask -> {
                viewModelScope.launch {
                    getAllTask()
                }
            }

            is HomeEvents.EditTask -> {
                viewModelScope.launch {
                    try {
                        updateTask(event.task)
                        _state.update {
                            it.copy(
                                isLoading = false,
                                success = "Task Updated Successfully",
                                error = ""
                            )
                        }
                    } catch (e: Exception) {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                success = "",
                                error = "Error ${e.message}"
                            )
                        }
                    }
                }
            }

            is HomeEvents.AddTask -> {
                viewModelScope.launch {
                    try {
                        insertTask(event.task)
                        _state.update {
                            it.copy(
                                isLoading = false,
                                success = "Task Added Successfully",
                                error = ""
                            )
                        }
                    } catch (e: Exception) {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                success = "",
                                error = "Error ${e.message}"
                            )
                        }
                    }

                    getAllTask()
                }
            }

            is HomeEvents.DeleteTask -> {
                viewModelScope.launch {
                    try {
                        deleteTask(event.task)
                        _state.update {
                            it.copy(
                                isLoading = false,
                                success = "Task Deleted Successfully",
                                error = ""
                            )
                        }
                    } catch (e: Exception) {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                success = "",
                                error = "Error ${e.message}"
                            )
                        }
                    }
                }
            }
        }
    }

    private suspend fun getAllTask() {
        getTaskRepository.getTask().collect { taskList ->
            _state.update { it.copy(isLoading = false, data = taskList) }
        }
    }

    private suspend fun updateTask(task: TaskEntity) {
        getTaskRepository.updateTask(task)
        getAllTask()
    }

    private suspend fun insertTask(task: TaskEntity) {
        getTaskRepository.insertTask(listOf(task))
        _state.update {
            it.copy(
                data = _state.value.data + task
            )
        }
        getAllTask()
    }

    private suspend fun deleteTask(task: TaskEntity) {
        getTaskRepository.deleteTask(task)
        _state.update {
            it.copy(
                data = _state.value.data - task
            )
        }
    }

}