package com.example.jetpackcomposetutorial.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackcomposetutorial.Screen
import com.example.jetpackcomposetutorial.TranslateUnitNumber
import com.example.jetpackcomposetutorial.listOfEveryTask
import com.example.jetpackcomposetutorial.viewModel

@Composable
fun AllUnitsScreen(
    navController: NavController
) {

    val scrollState = rememberScrollState()

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(20.dp)
    ){

        for (i in 1..36)
        Button(onClick = {
            viewModel.id = (listOfEveryTask.count()/36)*(i-1)+1
            navController.navigate(route = Screen.Home.route)
        },
            modifier = Modifier.padding(10.dp),
            //colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(text = "${TranslateUnitNumber(i)}", fontSize = 30.sp)
        }

    }


}

