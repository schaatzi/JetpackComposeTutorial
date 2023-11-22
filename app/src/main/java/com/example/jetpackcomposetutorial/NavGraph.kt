package com.example.jetpackcomposetutorial

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackcomposetutorial.Screens.AllUnitsScreen
import com.example.jetpackcomposetutorial.Screens.DetailScreen
import com.example.jetpackcomposetutorial.Screens.HomeScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){

        composable(
            route = Screen.Home.route
        ){
            HomeScreen(navController = navController)
        }

        composable(
            route = Screen.Detail.route
        ){
            DetailScreen(navController = navController)
        }

        composable(
            route = Screen.All.route
        ){
            AllUnitsScreen(navController = navController)
        }

    }
}