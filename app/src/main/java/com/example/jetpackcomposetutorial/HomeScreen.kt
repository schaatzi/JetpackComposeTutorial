package com.example.jetpackcomposetutorial

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.ui.input.ImeAction
import androidx.ui.input.KeyboardType
import androidx.ui.text.toUpperCase

var waitForChange = false



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {

    //var oldDetails = listOfEveryTask.get(viewModel.id).details
    // var oldNeedSub

    var text by remember {
        mutableStateOf("")
    }

    var tasksPerUnit = listOfEveryTask.count()/36

    val context = LocalContext.current  //used by toast

    var blankNotesIfNull: String? = ""

    viewModel.incrementId()
    viewModel.decrementId()

    println("this is the home screen before the first composable block. tasksPerUnit: $tasksPerUnit")

    println("notes to string: ${listOfEveryTask.get(viewModel.id).notes.orEmpty()}")

    Row (
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            //.background(color = Color(0xFF192655))
            .padding(20.dp),
    ){

        Text(
            text = "Unit: ${TranslateUnitNumber(listOfEveryTask.get(viewModel.id).unitNum)}",
            fontSize = 25.sp,
            color = Color(0xFF3876BF),
            modifier = Modifier
                .padding(10.dp)
            //.align(Alignment.Start)
        )

        Button(
            onClick = {//go to all units

                navController.navigate(route = Screen.All.route)
            },
            modifier = Modifier.padding(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF3F0CA)),
        ) {
            Text("see all units",
                fontSize = 15.sp,
                color = Color(0xFF3876BF))
            //Text(text = "See all units    >",
                //fontSize = 10.sp)
        }

        Button(onClick = {//go to list

            navController.navigate(route = Screen.Detail.route)
        },
            modifier = Modifier.padding(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF3F0CA))
        ) {
            Text("see list",
                fontSize = 15.sp,
                color = Color(0xFF3876BF))
        }

    }



    Column (
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ){
        Text(//previous task text
            text = "[ ${pullComplete(-1)} ] ${pullRoom(-1)} ${pullDetails(-1)}} ${literalNullNotesToBlank(-1,-1)}      id: ${viewModel.id} / ${listOfEveryTask.count()}",
            fontSize = 15.sp,
            color = Color(0xFF3876BF),
            //modifier = Modifier
                //.align(Alignment.Start)
                //.padding(20.dp),
            style = LocalTextStyle.current.copy(lineHeight = 25.sp),
            //modifier = Modifier.align(Alignment.Start)
            modifier = Modifier.align(Alignment.Start)
        )

        Text(//current task text//////////////////////////////////////////////////////////
            //text = "${listOfEveryTask.get(viewModel.id).details}",
            text = "[ ${pullComplete(0)} ] ${pullRoom(0)} ${pullDetails(0)} ${literalNullNotesToBlank(0,0)}",
            fontSize = 30.sp,
            style = LocalTextStyle.current.copy(lineHeight = 50.sp),
            color = Color(0xFF3876BF),
            modifier = Modifier.align(Alignment.Start)
        )////////////////////////////////////////////////////////////////////////////////////


        TextField(

            value = text,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                unfocusedLabelColor = Color(0xFF3876BF),
                focusedLabelColor = Color(0xFF3876BF),
                focusedIndicatorColor = Color(0xFF3876BF),
                unfocusedIndicatorColor = Color(0xFF3876BF)
            ),
            //colors = TextFieldDefaults.textFieldColors(textColor = Color(0xFF3876BF)),
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
                            viewModel.incrementId()
                            viewModel.decrementId()
                        } else {
                            viewModel.incrementId()
                            databaseGet                     //after going to the next task, set the previous task to the entered text
                                .child("tasks")
                                    .child((viewModel.id - 1).toString())           //need to add a listener for this child and all children that do not match the current viewModel.id!!!
                                        .child("notes").setValue(text.uppercase())
                            viewModel.decrementId()
                            if (listOfEveryTask.get(viewModel.id).unitNum == listOfEveryTask.get(viewModel.id + 1).unitNum
                            ) {
                                viewModel.incrementId()
                            }
                        }
                        text = ""
                    }
                }
            )
        )




        Button(onClick = {//check and go to next
            //listOfEveryTask.get(viewModel.id).complete = "X"

            if(listOfEveryTask.get(viewModel.id).unitNum == listOfEveryTask.get(viewModel.id+1).unitNum){
                viewModel.incrementId()
                databaseGet                     //after going to the next task, set the previous task to complete
                    .child("tasks")
                        .child((viewModel.id - 1).toString())           //need to add a listener for this child and all children that do not match the current viewModel.id!!!
                            .child("complete").setValue("X")
            }

            else {
                viewModel.incrementId()
                viewModel.decrementId()
                Toast.makeText(context,
                    "no more items!",
                    Toast.LENGTH_SHORT).show()
            }

        },
            modifier = Modifier.padding(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE1AA74))
        ) {
            Text(text = "[X]  Check + go to next  v", fontSize = 30.sp)
        }

        Button(onClick = {//N/A and go to next
            //listOfEveryTask.get(viewModel.id).complete = "N/A"
            if(listOfEveryTask.get(viewModel.id).unitNum == listOfEveryTask.get(viewModel.id+1).unitNum){
                viewModel.incrementId()
                databaseGet                     //after going to the next task, set the previous task to complete
                    .child("tasks")
                        .child((viewModel.id - 1).toString())           //need to add a listener for this child and all children that do not match the current viewModel.id!!!
                            .child("complete").setValue("N/A")
            }else {
                viewModel.incrementId()
                viewModel.decrementId()
                Toast.makeText(context,
                    "no more items!",
                    Toast.LENGTH_SHORT).show()
            }
        },
            //modifier = Modifier.padding(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE1AA74))
        ) {
            Text(text = "N/A + go to next", fontSize = 20.sp)
        }

        Button(onClick = {//check
            //listOfEveryTask.get(viewModel.id).complete = "X"
            pushTask("complete", "X")
            if(listOfEveryTask.get(viewModel.id).unitNum == listOfEveryTask.get(viewModel.id+1).unitNum){
                viewModel.incrementId()
                viewModel.decrementId()

            } else {
                viewModel.decrementId()
                viewModel.incrementId()
            }
        },
            //modifier = Modifier.padding(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3876BF))
        ) {
            Text(text = "Check   [X]", fontSize = 20.sp)
        }

        Button(onClick = {//uncheck
            //listOfEveryTask.get(viewModel.id).complete = "  "
            pushTask("complete", "  ")
            if(listOfEveryTask.get(viewModel.id).unitNum == listOfEveryTask.get(viewModel.id+1).unitNum){
                viewModel.incrementId()
                viewModel.decrementId()
            } else {
                viewModel.decrementId()
                viewModel.incrementId()
            }
        },
            //modifier = Modifier.padding(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3876BF))
        ) {
            Text(text = "Uncheck   [ ]", fontSize = 20.sp)
        }

        Button(onClick = {//go to previous
            if((viewModel.id + tasksPerUnit - 1) % tasksPerUnit != 0) {
                viewModel.decrementId()
            }
        },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3876BF))
            //modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "Go to previous item   ^", fontSize = 20.sp)
        }

        Button(onClick = {//go to next
            if((viewModel.id + tasksPerUnit + 1) % tasksPerUnit != 0) {
                viewModel.incrementId()
            } else {
                Toast.makeText(context,
                    "no more items!",
                    Toast.LENGTH_SHORT).show()
            }

        },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3876BF))
            //modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "Go to next item   v", fontSize = 20.sp)
        }


    }


}
