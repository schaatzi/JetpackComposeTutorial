package com.example.jetpackcomposetutorial

//overwrites new task data to firebase
fun pushTask(path: String, data: String){
    databaseGet
        .child("tasks")
            .child((viewModel.id).toString())
                .child(path).setValue(data)
}