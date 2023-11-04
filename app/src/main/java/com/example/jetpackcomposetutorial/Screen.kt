package com.example.jetpackcomposetutorial

sealed class Screen (val route: String){
    object MainScreen: Screen("main_screen")
    object Unit101: Screen("unit_101")

}
