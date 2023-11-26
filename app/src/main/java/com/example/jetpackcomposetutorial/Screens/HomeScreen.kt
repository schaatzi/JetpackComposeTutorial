package com.example.jetpackcomposetutorial.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackcomposetutorial.Screen
import com.example.jetpackcomposetutorial.TranslateUnitNumber
import com.example.jetpackcomposetutorial.databaseGet
import com.example.jetpackcomposetutorial.listOfEveryTask
import com.example.jetpackcomposetutorial.literalNullNotesToBlank
import com.example.jetpackcomposetutorial.nullToBlank
import com.example.jetpackcomposetutorial.pullComplete
import com.example.jetpackcomposetutorial.pullDetails
import com.example.jetpackcomposetutorial.pullNotes
import com.example.jetpackcomposetutorial.pullRoom
import com.example.jetpackcomposetutorial.pushTask
import com.example.jetpackcomposetutorial.sendMail
import com.example.jetpackcomposetutorial.viewModel

var waitForChange = false



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {


    var text by remember {
        mutableStateOf("")
    }

    var tasksPerUnit = listOfEveryTask.count()/36

    val context = LocalContext.current  //used by toast

    var blankNotesIfNull: String? = ""

    var bottomButtonsColor = 0xFF3876BF
    var readOnlyTextColor = bottomButtonsColor
    var middleButtonsColor = 0xFFE1AA74

    com.example.jetpackcomposetutorial.viewModel.incrementId()
    com.example.jetpackcomposetutorial.viewModel.decrementId()



    Column (
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row (
            verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Center,
        ){
//
            Text(
                text = "Unit: ${TranslateUnitNumber(listOfEveryTask.get(viewModel.id).unitNum)}",            fontSize = 25.sp,            color = Color(0xFF3876BF),
                modifier = Modifier
                    .padding(10.dp)
            )
//
            Button(
                onClick = {//go to all units
                    navController.navigate(route = Screen.All.route)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(bottomButtonsColor)),
                modifier = Modifier.padding(5.dp)

            ) {
                Text("see all units",
                    fontSize = 15.sp)
            }
//
            Button(onClick = {//go to list
                navController.navigate(route = Screen.Detail.route)
            },
                colors = ButtonDefaults.buttonColors(containerColor = Color(bottomButtonsColor)),
                modifier = Modifier.padding(5.dp)
            ) {
                Text("see list",
                    fontSize = 15.sp)
            }

        }
/*
        Text(//task#
            text = "Task: ${com.example.jetpackcomposetutorial.viewModel.id}", fontSize = 15.sp, color = Color(readOnlyTextColor),
            //style = LocalTextStyle.current.copy(lineHeight = 25.sp),
            modifier = Modifier.align(Alignment.Start)
        )

 */
        Text(//header
            text = "Previous task:", fontSize = 20.sp, color = Color(readOnlyTextColor),
            //style = LocalTextStyle.current.copy(lineHeight = 25.sp),
            modifier = Modifier.align(Alignment.Start)
        )

        Text(//previous task text
            text = "[ ${pullComplete(-1)} ] ${pullRoom(-1)} ${pullDetails(-1)} ${nullToBlank(pullNotes(-1))} ", fontSize = 20.sp, color = Color(readOnlyTextColor),
            style = LocalTextStyle.current.copy(lineHeight = 25.sp),
            modifier = Modifier.align(Alignment.Start)
        )

    }

/////////////////////

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ){

    }

//////////////////////

    Column (
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ){


        Text(//current task room
            text = "[ ${pullComplete(0)} ] ${pullRoom(0)}", fontSize = 30.sp, color = Color(readOnlyTextColor),
            //style = LocalTextStyle.current.copy(lineHeight = 50.sp),
            modifier = Modifier.align(Alignment.Start)
        )

        Box(//current task details
            modifier = Modifier
            //.fillMaxSize()
            .padding(10.dp)
            .height(100.dp)
        ){
            Text(
                text = "${pullDetails(0)} ${nullToBlank(pullNotes(0))}", fontSize = 30.sp, color = Color(readOnlyTextColor),
                modifier = Modifier
                    .wrapContentHeight()
                    .align(Alignment.TopStart)
                //style = LocalTextStyle.current.copy(lineHeight = 50.sp),
                //modifier = Modifier.align(Alignment.Start)
            )
        }

        TextField(

            value = text,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                unfocusedLabelColor = Color(readOnlyTextColor),
                focusedLabelColor = Color(readOnlyTextColor),
                focusedIndicatorColor = Color(readOnlyTextColor),
                unfocusedIndicatorColor = Color(readOnlyTextColor)
            ),
            onValueChange = { newText -> text = newText },
            label = {Text("add notes to this task")},
            placeholder = { Text(text = "(type 'del' to erase entry)",
                color = Color.Gray)},
            keyboardOptions = KeyboardOptions(
                keyboardType = androidx.compose.ui.text.input.KeyboardType.Email,
                imeAction = androidx.compose.ui.text.input.ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (text != ""){
                        if (text == "del"){
                            pushTask("notes","")
                            com.example.jetpackcomposetutorial.viewModel.incrementId()
                            com.example.jetpackcomposetutorial.viewModel.decrementId()
                        } else {
                            com.example.jetpackcomposetutorial.viewModel.incrementId()
                            databaseGet                     //after going to the next task, set the previous task to the entered text
                                .child("tasks")
                                    .child((com.example.jetpackcomposetutorial.viewModel.id - 1).toString())           //need to add a listener for this child and all children that do not match the current viewModel.id!!!
                                        .child("notes").setValue(text.uppercase())
                            com.example.jetpackcomposetutorial.viewModel.decrementId()
                            if (listOfEveryTask.get(com.example.jetpackcomposetutorial.viewModel.id).unitNum == listOfEveryTask.get(
                                    com.example.jetpackcomposetutorial.viewModel.id + 1).unitNum
                            ) {
                                com.example.jetpackcomposetutorial.viewModel.incrementId()
                            }
                        }
                        text = ""
                    }
                }
            )
        )




        Button(onClick = {//check and go to next
            //listOfEveryTask.get(viewModel.id).complete = "X"

            if(listOfEveryTask.get(com.example.jetpackcomposetutorial.viewModel.id).unitNum == listOfEveryTask.get(
                    com.example.jetpackcomposetutorial.viewModel.id+1).unitNum){
                com.example.jetpackcomposetutorial.viewModel.incrementId()
                databaseGet                     //after going to the next task, set the previous task to complete
                    .child("tasks")
                        .child((com.example.jetpackcomposetutorial.viewModel.id - 1).toString())           //need to add a listener for this child and all children that do not match the current viewModel.id!!!
                            .child("complete").setValue("X")
            }

            else {
                com.example.jetpackcomposetutorial.viewModel.incrementId()
                com.example.jetpackcomposetutorial.viewModel.decrementId()
                Toast.makeText(context,
                    "no more items!",
                    Toast.LENGTH_SHORT).show()
            }

        },
            modifier = Modifier.padding(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(middleButtonsColor))
        ) {
            Text(text = "[X]  Check + go to next ", fontSize = 30.sp)
        }

        Button(onClick = {//N/A and go to next
            //listOfEveryTask.get(viewModel.id).complete = "N/A"
            if(listOfEveryTask.get(com.example.jetpackcomposetutorial.viewModel.id).unitNum == listOfEveryTask.get(
                    com.example.jetpackcomposetutorial.viewModel.id+1).unitNum){
                com.example.jetpackcomposetutorial.viewModel.incrementId()
                databaseGet                     //after going to the next task, set the previous task to complete
                    .child("tasks")
                        .child((com.example.jetpackcomposetutorial.viewModel.id - 1).toString())           //need to add a listener for this child and all children that do not match the current viewModel.id!!!
                            .child("complete").setValue("N/A")
            }else {
                com.example.jetpackcomposetutorial.viewModel.incrementId()
                com.example.jetpackcomposetutorial.viewModel.decrementId()
                Toast.makeText(context,
                    "no more items!",
                    Toast.LENGTH_SHORT).show()
            }
        },
            //modifier = Modifier.padding(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(middleButtonsColor))
        ) {
            Text(text = "N/A + go to next", fontSize = 20.sp)
        }


        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Column (
                modifier = Modifier
                    .padding(20.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {
                Button(onClick = {//check
                    //listOfEveryTask.get(viewModel.id).complete = "X"
                    pushTask("complete", "X")
                    if(listOfEveryTask.get(com.example.jetpackcomposetutorial.viewModel.id).unitNum == listOfEveryTask.get(
                            com.example.jetpackcomposetutorial.viewModel.id+1).unitNum){
                        com.example.jetpackcomposetutorial.viewModel.incrementId()
                        com.example.jetpackcomposetutorial.viewModel.decrementId()

                    } else {
                        com.example.jetpackcomposetutorial.viewModel.decrementId()
                        com.example.jetpackcomposetutorial.viewModel.incrementId()
                    }
                },
                    //modifier = Modifier.padding(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(bottomButtonsColor))
                ) {
                    Text(text = "[X]", fontSize = 20.sp)
                }

                Button(onClick = {//uncheck
                    //listOfEveryTask.get(viewModel.id).complete = "  "
                    pushTask("complete", "  ")
                    if(listOfEveryTask.get(com.example.jetpackcomposetutorial.viewModel.id).unitNum == listOfEveryTask.get(
                            com.example.jetpackcomposetutorial.viewModel.id+1).unitNum){
                        com.example.jetpackcomposetutorial.viewModel.incrementId()
                        com.example.jetpackcomposetutorial.viewModel.decrementId()
                    } else {
                        com.example.jetpackcomposetutorial.viewModel.decrementId()
                        com.example.jetpackcomposetutorial.viewModel.incrementId()
                    }
                },
                    //modifier = Modifier.padding(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(bottomButtonsColor))
                ) {
                    Text(text = "[  ]", fontSize = 20.sp)
                }
            }

            Column (
                modifier = Modifier
                    .padding(20.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                Button(onClick = {//go to previous
                    if((com.example.jetpackcomposetutorial.viewModel.id + tasksPerUnit - 1) % tasksPerUnit != 0) {
                        com.example.jetpackcomposetutorial.viewModel.decrementId()
                    }
                },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(bottomButtonsColor))
                    //modifier = Modifier.padding(5.dp)
                ) {
                    Text(text = "^", fontSize = 20.sp)
                }

                Button(onClick = {//go to next
                    if((com.example.jetpackcomposetutorial.viewModel.id + tasksPerUnit + 1) % tasksPerUnit != 0) {
                        com.example.jetpackcomposetutorial.viewModel.incrementId()
                    } else {
                        Toast.makeText(context,
                            "no more items!",
                            Toast.LENGTH_SHORT).show()
                    }

                },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(bottomButtonsColor))
                    //modifier = Modifier.padding(5.dp)
                ) {
                    Text(text = "v", fontSize = 20.sp)
                }
            }
        }



    }





}
