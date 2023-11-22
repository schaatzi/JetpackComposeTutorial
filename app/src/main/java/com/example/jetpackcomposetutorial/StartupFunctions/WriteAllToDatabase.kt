package com.example.jetpackcomposetutorial.StartupFunctions

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import com.example.jetpackcomposetutorial.database
import com.example.jetpackcomposetutorial.listOfEveryTask


fun WriteAllToDatabase() {

    for (i in 1..listOfEveryTask.count()) {
        val myRef = database.getReference("tasks")
        myRef.child("${i - 1}").setValue(listOfEveryTask.get(i - 1))  //sets value to firebase that is one object from listOfEveryTask
    }

}



