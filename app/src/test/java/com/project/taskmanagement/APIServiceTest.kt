package com.project.taskmanagement

import com.project.taskmanagement.data.response.GetTaskResponseItem
import com.project.taskmanagement.network.APIService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class APIServiceTest {

    private lateinit var apiService: APIService

    @Before
    fun setUp() {
        apiService = mockk(relaxed = true)
    }

    @Test
    fun testCorrectTaskIdAndTitle() = runBlocking {
        val mockResponse = listOf(GetTaskResponseItem(completed = true, id = 1, title = "delectus aut autem", userId = 1))
        coEvery { apiService.getTodos() } returns mockResponse

        val response = apiService.getTodos()

        assertEquals(1, response.first().id)
        assertEquals("delectus aut autem", response.last().title)    }
}
