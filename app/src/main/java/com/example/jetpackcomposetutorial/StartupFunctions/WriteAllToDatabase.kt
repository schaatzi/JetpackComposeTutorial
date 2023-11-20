package com.example.jetpackcomposetutorial.StartupFunctions

import com.example.jetpackcomposetutorial.database
import com.example.jetpackcomposetutorial.listOfEveryTask


fun WriteAllToDatabase() {

    for (i in 1..listOfEveryTask.count()) {
        val myRef = database.getReference("tasks")
        myRef.child("${i - 1}").setValue(listOfEveryTask.get(i - 1))  //sets value to firebase that is one object from listOfEveryTask
    }



}

