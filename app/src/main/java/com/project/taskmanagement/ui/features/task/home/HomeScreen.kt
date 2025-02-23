package com.project.taskmanagement.ui.features.task.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.taskmanagement.R
import com.project.taskmanagement.database.TaskEntity
import com.project.taskmanagement.firebase.AnalyticHelper
import com.project.taskmanagement.firebase.AnalyticsEvents

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel,
    modifier: Modifier,
) {
    val homeState = homeScreenViewModel.state.collectAsState().value
    val context = LocalContext.current
    val listState = rememberLazyListState()

    LaunchedEffect(Unit, key2 = homeState.data.size) {
        homeScreenViewModel.onEvent(HomeEvents.GetAllTask(isFirstLoaded = true))
        AnalyticHelper.logEvent("HomeScreen")
    }

    LaunchedEffect(homeState.success) {
        if (homeState.success.isNotEmpty()) {
            Toast.makeText(context, homeState.success, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(homeState.error) {
        if (homeState.error.isNotEmpty()) {
            Toast.makeText(context, homeState.error, Toast.LENGTH_SHORT).show()
        }
    }
//    LaunchedEffect(homeState.data.size) {
//        if (homeState.data.isNotEmpty()) {
//            listState.animateScrollToItem(homeState.data.size - 1)
//        }
//    }
    var taskTitle by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(top = 80.dp)
    ) {
        Text(
            text = stringResource(R.string.task_manager),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = taskTitle,
            onValueChange = { taskTitle = it },
            label = {
                Text(
                    stringResource(R.string.enter_task_title),
                    color = Color.Black
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(8.dp)
                ),
            textStyle = TextStyle(
                color = Color.Black
            )


        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (taskTitle.isNotBlank()) {
                    homeScreenViewModel.onEvent(
                        HomeEvents.AddTask(
                            task = TaskEntity(
                                title = taskTitle
                            )
                        )
                    )
                    AnalyticHelper.logTaskEvent(
                        eventName = AnalyticsEvents.ADD_TASK,
                        taskName = taskTitle
                    )
                    homeScreenViewModel.onEvent(HomeEvents.GetAllTask(isFirstLoaded = false))
                    taskTitle = ""
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0088D1),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)

        ) {
            Text(stringResource(R.string.add_task))
        }
//        Button(
//            onClick = { homeScreenViewModel.forceDbCrash() },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Force Database Crash")
//        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            state = listState
        ) {
            items(homeState.data, key = { it.id }) { task ->
                TaskItem(
                    task = task,
                    onEditClick = { updatedTask ->
                        homeScreenViewModel.onEvent(
                            HomeEvents.EditTask(
                                updatedTask
                            )
                        )
                        AnalyticHelper.logTaskEvent(
                            eventName = AnalyticsEvents.EDIT_TASK,
                            taskName = updatedTask.title
                        )
                    },
                    onCompleteClick = { updatedTask ->
                        homeScreenViewModel.onEvent(
                            HomeEvents.EditTask(
                                updatedTask
                            )
                        )
                        AnalyticHelper.logTaskEvent(
                            eventName = AnalyticsEvents.COMPLETE_TASK,
                            taskName = updatedTask.title
                        )
                    },
                    onDeleteClick = { deletedTask ->
                        homeScreenViewModel.onEvent(
                            HomeEvents.DeleteTask(
                                deletedTask
                            )
                        )
                        AnalyticHelper.logTaskEvent(
                            eventName = AnalyticsEvents.DELETE_TASK,
                            taskName = deletedTask.title
                        )
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


@Composable
fun TaskItem(
    task: TaskEntity,
    onEditClick: (TaskEntity) -> Unit,
    onCompleteClick: (TaskEntity) -> Unit,
    onDeleteClick: (TaskEntity) -> Unit,
) {
    var taskTitle by remember { mutableStateOf(task.title) }
    var isCompleted by remember { mutableStateOf(task.completed) }
    var isEditing by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp)
                .wrapContentHeight()
                .background(if(isCompleted) Color(0xFF4CAF50)  else Color(0xFF0088D1) ),

            ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Task",
                        modifier = Modifier
                            .padding(16.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    IconButton(
                        onClick = {
                            if (isEditing) {
                                onEditClick(task.copy(title = taskTitle))
                            }
                            isEditing = !isEditing
                        }
                    ) {
                        Icon(
                            imageVector = if (isEditing) Icons.Filled.Save else Icons.Filled.Edit,
                            contentDescription = stringResource(R.string.edit_task),
                            tint = Color.White
                        )
                    }
                }
                TextField(
                    value = taskTitle,
                    onValueChange = { taskTitle = it },
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Transparent),
                    readOnly = !isEditing,
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    maxLines = 3,

                )
            }

            Row(
                modifier = Modifier.align(Alignment.BottomStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isCompleted,
                    onCheckedChange = { checked ->
                        isCompleted = checked
                        onCompleteClick(task.copy(completed = checked))
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.White,
                        uncheckedColor = Color.White
                    )

                )
                Text(
                    text = stringResource(R.string.mark_as_completed),
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.White
                )
            }
            IconButton(
                onClick = {
                   onDeleteClick(task)
                },
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.edit_task),
                    tint = Color.Red
                )
            }
        }
    }
}
