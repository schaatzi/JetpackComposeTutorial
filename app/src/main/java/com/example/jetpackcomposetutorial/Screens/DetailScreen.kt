package com.example.jetpackcomposetutorial.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackcomposetutorial.Screen
import com.example.jetpackcomposetutorial.TranslateUnitNumber
import com.example.jetpackcomposetutorial.indexComplete
import com.example.jetpackcomposetutorial.indexDetails
import com.example.jetpackcomposetutorial.indexNotes
import com.example.jetpackcomposetutorial.indexRoom
import com.example.jetpackcomposetutorial.listOfEveryTask
import com.example.jetpackcomposetutorial.literalNullNotesToBlank
import com.example.jetpackcomposetutorial.nullToBlank
import com.example.jetpackcomposetutorial.sendMail
import com.example.jetpackcomposetutorial.viewModel

@Composable
fun DetailScreen(
    navController: NavController
) {

    var buildingNum = "B3"

    var currentUnit by remember {
        mutableStateOf(listOfEveryTask.get(viewModel.id).unitNum)
    }

    val scrollState = rememberScrollState()
    var firstIdOfUnit = 0
    var lastIdOfUnit = 0
    var tasksPerUnit = 0
    val context = LocalContext.current

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

        var emailBody = ""
        for (i in firstIdOfUnit..lastIdOfUnit){
            emailBody = emailBody + "(" + indexComplete(i) + ")" + indexRoom(i) + indexDetails(i) + nullToBlank(indexNotes(i)) +"\n" + "\n"
        }


        Text(
            text = "Tap here to send email of this list",
            fontSize = 22.sp,
            modifier = Modifier
                .border(BorderStroke(3.dp, color = Color.Green))
                .padding(5.dp)
                .clickable {
                context.sendMail("jfriemoth@livepreferred.com","$buildingNum ${TranslateUnitNumber(listOfEveryTask.get(viewModel.id).unitNum)}",emailBody)
            }
        )

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
                text = "[ ${listOfEveryTask.get(i).complete} ] ${listOfEveryTask.get(i).room}  ${listOfEveryTask.get(i).details} ${nullToBlank(indexNotes(i))}",
                fontSize = 15.sp,
                style = LocalTextStyle.current.copy(lineHeight = 30.sp)

            )
        }

        Text(
            text = "Tap here to send email of this list",
            fontSize = 22.sp,
            modifier = Modifier
                .border(BorderStroke(3.dp, color = Color.Green))
                .padding(5.dp)
                .clickable {
                    context.sendMail("jfriemoth@livepreferred.com","$buildingNum ${TranslateUnitNumber(listOfEveryTask.get(viewModel.id).unitNum)}",emailBody)
                }
        )
    }

    /*
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        val itemCount = listOfEveryTask.count()/36
        items(itemCount){

        }
    }

     */


}