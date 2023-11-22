package com.example.jetpackcomposetutorial.StartupFunctions

import com.example.jetpackcomposetutorial.databaseGet
import com.example.jetpackcomposetutorial.listOfEveryTask
import com.example.jetpackcomposetutorial.Screens.waitForChange
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

/*
fun saveOldParameter(parameter: String): String{
    listOfEveryTask.get(viewModel.id).parameter
}

 */

fun OnDatabaseChange(){

    var getData = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {

                listOfEveryTask.get(com.example.jetpackcomposetutorial.viewModel.id).complete =
                    snapshot.child("tasks").child(com.example.jetpackcomposetutorial.viewModel.id.toString()).child("complete").getValue().toString()

                    listOfEveryTask.get(com.example.jetpackcomposetutorial.viewModel.id - 1).complete =
                        snapshot.child("tasks").child((com.example.jetpackcomposetutorial.viewModel.id - 1).toString()).child("complete").getValue().toString()

                //param called concreteItem goes here

                listOfEveryTask.get(com.example.jetpackcomposetutorial.viewModel.id).details =
                    snapshot.child("tasks").child(com.example.jetpackcomposetutorial.viewModel.id.toString()).child("details").getValue().toString()

                listOfEveryTask.get(com.example.jetpackcomposetutorial.viewModel.id).needSub =
                    snapshot.child("tasks").child(com.example.jetpackcomposetutorial.viewModel.id.toString()).child("needSub").getValue().toString()

                listOfEveryTask.get(com.example.jetpackcomposetutorial.viewModel.id).notes =
                    snapshot.child("tasks").child(com.example.jetpackcomposetutorial.viewModel.id.toString()).child("notes").getValue().toString()

                    listOfEveryTask.get(com.example.jetpackcomposetutorial.viewModel.id - 1).notes =
                        snapshot.child("tasks").child((com.example.jetpackcomposetutorial.viewModel.id - 1).toString()).child("notes").getValue().toString()

                listOfEveryTask.get(com.example.jetpackcomposetutorial.viewModel.id).room =
                    snapshot.child("tasks").child(com.example.jetpackcomposetutorial.viewModel.id.toString()).child("room").getValue().toString()

                //param called taskId goes here

                listOfEveryTask.get(com.example.jetpackcomposetutorial.viewModel.id).unitNum =
                    snapshot.child("tasks").child(com.example.jetpackcomposetutorial.viewModel.id.toString()).child("unitNum").getValue().toString().toInt()

                println("snowballs viewModel.id: ${com.example.jetpackcomposetutorial.viewModel.id}")
                println("snowballs firebase: ${snapshot.child("tasks").child(com.example.jetpackcomposetutorial.viewModel.id.toString()).child("complete").getValue().toString()}")
                println("snowballs listOfEveryTask:  ${listOfEveryTask.get(com.example.jetpackcomposetutorial.viewModel.id).complete}")

                waitForChange = true


            //databaseGet.child("2").child("complete").setValue("1")
            //listOfEveryTask.get(1).complete = snapshot.child("1").child("complete").getValue().toString()
            //databaseGet.child("1").child("complete").setValue("")

            //println("snapshot.children: ${snapshot.children}")

        }

        override fun onCancelled(error: DatabaseError) {

        }

    }


    databaseGet.addValueEventListener(getData)
    databaseGet.addListenerForSingleValueEvent(getData)
}