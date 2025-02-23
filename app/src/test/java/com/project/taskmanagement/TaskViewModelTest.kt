package com.project.taskmanagement

import com.project.taskmanagement.data.repository.GetTaskRepository
import com.project.taskmanagement.database.TaskEntity
import com.project.taskmanagement.ui.features.task.home.HomeEvents
import com.project.taskmanagement.ui.features.task.home.HomeScreenViewModel
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeScreenViewModelTest {

    private lateinit var viewModel: HomeScreenViewModel
    private val getTaskRepository: GetTaskRepository = mockk(relaxed = true)
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    @Before
    fun setUp() {
        every { getTaskRepository.getTask() } returns flowOf(emptyList())
        viewModel = HomeScreenViewModel(getTaskRepository)
    }

    @Test
    fun testGetTask() = runTest {
        viewModel.onEvent(HomeEvents.GetAllTask(isFirstLoaded = true))
        advanceUntilIdle()
        coVerify { getTaskRepository.getTask() }
    }
    @Test
    fun testUpdateTask() = runTest {
        viewModel.onEvent(HomeEvents.EditTask(TaskEntity(
            id = 1,
            title = "title",
            completed = true
        )))
        advanceUntilIdle()
        coVerify { getTaskRepository.updateTask(
            TaskEntity(
                id = 1,
                title = "title",
                completed = true
            )
        ) }
    }
}