package com.example.jetpackcomposetutorial

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {



    var currentUnit by remember {
        mutableStateOf(listOfEveryTask.get(viewModel.id).unitNum)
    }

    var text by remember {
        mutableStateOf("")
    }

    var tasksPerUnit = listOfEveryTask.count()/36

    val context = LocalContext.current


    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ){

        Button(onClick = {//go to all units

            navController.navigate(route = Screen.All.route)
        },
            //modifier = Modifier.padding(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(text = "See all units    <", fontSize = 20.sp)
        }

        Button(onClick = {//go to list

            navController.navigate(route = Screen.Detail.route)
        },
            //modifier = Modifier.padding(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
        ) {
            Text(text = "See whole list    <", fontSize = 20.sp)
        }

        Text(
            text = "Unit: ${TranslateUnitNumber(listOfEveryTask.get(viewModel.id).unitNum)}  id: ${viewModel.id} / ${listOfEveryTask.count()}",
            fontSize = 40.sp,
            modifier = Modifier.align(Alignment.Start)
        )


    }



    Column (
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ){
        Text(//previous task text
            text = "[ ${listOfEveryTask.get(viewModel.id - 1).complete} ] ${listOfEveryTask.get(viewModel.id -1).notes} ${listOfEveryTask.get(
                viewModel.id - 1).details}",
            fontSize = 15.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(20.dp),
            style = LocalTextStyle.current.copy(lineHeight = 25.sp)
        )



        Text(//current task text//////////////////////////////////////////////////////////
            text = "[ ${listOfEveryTask.get(viewModel.id).complete} ] ${listOfEveryTask.get(viewModel.id).details} ${listOfEveryTask.get(viewModel.id).notes}",
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.Start),
            style = LocalTextStyle.current.copy(lineHeight = 50.sp)
        )////////////////////////////////////////////////////////////////////////////////////


        TextField(
            value = text,
            onValueChange = { newText -> text = newText },
            label = { Text(text = "add notes (type 'del' to erase)")},
            keyboardOptions = KeyboardOptions(
                keyboardType = androidx.compose.ui.text.input.KeyboardType.Email,
                imeAction = androidx.compose.ui.text.input.ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (text != ""){
                        if (text == "del"){
                            listOfEveryTask.get(viewModel.id).notes = ""
                            viewModel.incrementId()
                            viewModel.decrementId()
                        } else {
                            listOfEveryTask.get(viewModel.id).notes = text.uppercase()
                            viewModel.incrementId()
                            viewModel.decrementId()
                            if (listOfEveryTask.get(viewModel.id).unitNum == listOfEveryTask.get(
                                    viewModel.id + 1
                                ).unitNum
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
            listOfEveryTask.get(viewModel.id).complete = "X"
            if(listOfEveryTask.get(viewModel.id).unitNum == listOfEveryTask.get(viewModel.id+1).unitNum){
                viewModel.incrementId()
            }else {
                viewModel.incrementId()
                viewModel.decrementId()
                Toast.makeText(context,
                    "no more items!",
                    Toast.LENGTH_SHORT).show()
            }
        },
            modifier = Modifier.padding(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
        ) {
            Text(text = "Check + go to next", fontSize = 30.sp)
        }

        Button(onClick = {//N/A and go to next
            listOfEveryTask.get(viewModel.id).complete = "N/A"
            if(listOfEveryTask.get(viewModel.id).unitNum == listOfEveryTask.get(viewModel.id+1).unitNum){
                viewModel.incrementId()
            }else {
                viewModel.incrementId()
                viewModel.decrementId()
                Toast.makeText(context,
                    "no more items!",
                    Toast.LENGTH_SHORT).show()
            }
        },
            //modifier = Modifier.padding(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
        ) {
            Text(text = "N/A + go to next", fontSize = 20.sp)
        }

        Button(onClick = {//check
            listOfEveryTask.get(viewModel.id).complete = "X"
            if(listOfEveryTask.get(viewModel.id).unitNum == listOfEveryTask.get(viewModel.id+1).unitNum){
                viewModel.incrementId()
                viewModel.decrementId()
            } else {
                viewModel.decrementId()
                viewModel.incrementId()
            }
        },
            //modifier = Modifier.padding(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(text = "Check   [X]", fontSize = 20.sp)
        }

        Button(onClick = {//uncheck
            listOfEveryTask.get(viewModel.id).complete = "  "
            if(listOfEveryTask.get(viewModel.id).unitNum == listOfEveryTask.get(viewModel.id+1).unitNum){
                viewModel.incrementId()
                viewModel.decrementId()
            } else {
                viewModel.decrementId()
                viewModel.incrementId()
            }
        },
            //modifier = Modifier.padding(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(text = "Uncheck   [ ]", fontSize = 20.sp)
        }

        Button(onClick = {//go to previous
            if((viewModel.id + tasksPerUnit - 1) % tasksPerUnit != 0) {
                viewModel.decrementId()
            }
        },
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
            //modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "Go to next item   v", fontSize = 20.sp)
        }







    }


}
