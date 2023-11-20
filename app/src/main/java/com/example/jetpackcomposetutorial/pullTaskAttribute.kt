package com.example.jetpackcomposetutorial

fun pullComplete(idMod: Int): String{
    return listOfEveryTask.get(viewModel.id + idMod).complete
}

fun pullDetails(idMod: Int): String{
    return listOfEveryTask.get(viewModel.id + idMod).details
}

fun pullNotes(idMod: Int): String?{
    return listOfEveryTask.get(viewModel.id + idMod).notes
}

fun pullRoom(idMod: Int): String{
    return listOfEveryTask.get(viewModel.id + idMod).room
}

fun pullUnitNum(idMod: Int): Int{
    return listOfEveryTask.get(viewModel.id + idMod).unitNum
}