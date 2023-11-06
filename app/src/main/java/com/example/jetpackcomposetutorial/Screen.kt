package com.example.jetpackcomposetutorial

sealed class Screen (val route: String){
    object Home: Screen(route = "home_screen")
    object Detail: Screen(route = "detail_screen")
    object All: Screen(route = "all_screen")

}
