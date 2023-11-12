package com.example.jetpackcomposetutorial

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

fun InitialPull(){

    var getData = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            for (i in snapshot.children) {
                listOfEveryTask.get(i.key.toString().toInt() - 1).complete =
                    i.child("complete").getValue().toString()
                //param called concreteItem goes here
                listOfEveryTask.get(i.key.toString().toInt() - 1).details =
                    i.child("details").getValue().toString()
                listOfEveryTask.get(i.key.toString().toInt() - 1).needSub =
                    i.child("needSub").getValue().toString()
                listOfEveryTask.get(i.key.toString().toInt() - 1).notes =
                    i.child("notes").getValue().toString()
                //param called room goes here
                //param called taskId goes here
                listOfEveryTask.get(i.key.toString().toInt() - 1).unitNum =
                    i.child("unitNum").getValue().toString().toInt()
            }

            //databaseGet.child("2").child("complete").setValue("1")
            //listOfEveryTask.get(1).complete = snapshot.child("1").child("complete").getValue().toString()
            //databaseGet.child("1").child("complete").setValue("")

            println("snapshot.children: ${snapshot.children}")

        }

        override fun onCancelled(error: DatabaseError) {

        }

    }


    databaseGet.addValueEventListener(getData)
    databaseGet.addListenerForSingleValueEvent(getData)
}