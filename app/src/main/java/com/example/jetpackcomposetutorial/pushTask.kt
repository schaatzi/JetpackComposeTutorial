package com.example.jetpackcomposetutorial

//overwrites new task data to firebase
fun pushTask(path: String, data: String){
    databaseGet.child((viewModel.id+1).toString()).child(path).setValue(data)
}