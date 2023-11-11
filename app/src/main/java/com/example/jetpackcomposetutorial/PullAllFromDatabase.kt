package com.example.jetpackcomposetutorial

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


fun PullAllFromDatabase(){
    var getData = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            for (i in snapshot.children){
                listOfEveryTask.add(BabyTask(i.child("unitNum").getValue().toString().toInt(), i.child("details").getValue().toString()))
                var printable = i.child("unitNum").getValue().toString()
                var printable2 = i.child("details").getValue().toString()
                println("details: $printable $printable2")  //matches database
            }

            for (i in 1..listOfEveryTask.count()){
                println("loet: ${listOfEveryTask.get(i-1).unitNum} ${listOfEveryTask.get(i-1).details}")    //matches the entry from ResetAllAppTasks and not the database
            }

        }

        override fun onCancelled(error: DatabaseError) {

        }
    }

    databaseGet.addValueEventListener(getData)
    databaseGet.addListenerForSingleValueEvent(getData)
}