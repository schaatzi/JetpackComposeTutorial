package com.example.jetpackcomposetutorial

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DetailScreen(
    navController: NavController
) {


    var currentUnit by remember {
        mutableStateOf(listOfEveryTask.get(viewModel.id).unitNum)
    }



    val scrollState = rememberScrollState()

    var firstIdOfUnit = 0
    var lastIdOfUnit = 0
    var tasksPerUnit = 0

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(20.dp)

    ) {
//currentUnit-1)* listOfEveryTask.count()/30 + 1..(currentUnit-1)* listOfEveryTask.count() + listOfEveryTask.count()/30
        tasksPerUnit = listOfEveryTask.count()/36
        firstIdOfUnit = 1 +             tasksPerUnit*(currentUnit - 1)
        lastIdOfUnit = tasksPerUnit +   tasksPerUnit*(currentUnit - 1)

        for (i in firstIdOfUnit..lastIdOfUnit){
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(BorderStroke(2.dp, color = Color.Gray))
                    .clickable {
                        viewModel.id = i
                        navController.navigate(route = Screen.Home.route)
                    }
                    .padding(5.dp),
                text = "[ ${listOfEveryTask.get(i).complete} ] ${listOfEveryTask.get(i).details}",
                fontSize = 20.sp,
                style = LocalTextStyle.current.copy(lineHeight = 25.sp)

            )


        }
    }

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        val itemCount = listOfEveryTask.count()/36
        items(itemCount){

        }
    }


}