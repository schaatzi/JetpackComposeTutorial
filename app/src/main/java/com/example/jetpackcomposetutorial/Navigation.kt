package com.example.jetpackcomposetutorial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.core.TextField


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.Unit101.route + "/{name}",
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                    defaultValue = "Phillipp"
                    nullable = true
                }
            )
        ) { entry ->
            Unit101(name = entry.arguments?.getString("name"))
        }
    }

}

@Composable
fun MainScreen(navController: NavController) {
    var text by remember {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = text, onValueChange = {
            text = it
            }
        )
        Button(
            onClick = {
                navController.navigate(Screen.Unit101.route)
            },
            modifier = androidx.compose.ui.Modifier.align(Alignment.End)
        ) {
            Text(text = "To unit 101")
        }
    }

}

@Composable
fun Unit101 (name: String?){
    Column (
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(text = "unit 101")
    }

}