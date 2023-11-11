package com.example.jetpackcomposetutorial


fun WriteAllToDatabase() {

    for (i in 1..listOfEveryTask.count()) {
        val myRef = database.getReference("$i")
        myRef.setValue(listOfEveryTask.get(i - 1))  //sets value to firebase that is one object from listOfEveryTask
    }



}

