package com.example.jetpackcomposetutorial.StartupFunctions

import com.example.jetpackcomposetutorial.databaseGet
import com.example.jetpackcomposetutorial.listOfEveryTask
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

var startup = true

fun InitialPull(){

    var getData = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (startup == true) {
                for (i in snapshot.child("tasks").children) {
                    listOfEveryTask.get(i.key.toString().toInt()).complete =
                        i.child("complete").getValue().toString()
                    //param called concreteItem goes here
                    listOfEveryTask.get(i.key.toString().toInt()).details =
                        i.child("details").getValue().toString()
                    listOfEveryTask.get(i.key.toString().toInt()).needSub =
                        i.child("needSub").getValue().toString()
                    listOfEveryTask.get(i.key.toString().toInt()).notes =
                        i.child("notes").getValue().toString().orEmpty()
                    listOfEveryTask.get(i.key.toString().toInt()).room =
                        i.child("room").getValue().toString()
                    //param called taskId goes here
                    listOfEveryTask.get(i.key.toString().toInt()).unitNum =
                        i.child("unitNum").getValue().toString().toInt()
                }
            }
            startup = false
            println("snapshot.children: ${snapshot.children}")

        }

        override fun onCancelled(error: DatabaseError) {

        }

    }


    databaseGet.addValueEventListener(getData)
    databaseGet.addListenerForSingleValueEvent(getData)
}