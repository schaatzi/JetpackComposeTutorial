package com.example.jetpackcomposetutorial

class BabyTask (var unitNum: Int,  var details: String) : ChildTask(){

}

val listOfEveryTask = mutableListOf<BabyTask>()

fun CreateAllTasks(){

    for (i in 1..36){
        listOfEveryTask.add(BabyTask(i, "(unit list start)"))
        listOfEveryTask.add(BabyTask(i, "sink (stopper works) [far bath]"))
        listOfEveryTask.add(BabyTask(i, "sink (both waters work) [far bath]"))
        listOfEveryTask.add(BabyTask(i, "tub (water works) [far bath]"))
        listOfEveryTask.add(BabyTask(i, "island top (no chips) [kitchen]"))
        listOfEveryTask.add(BabyTask(i, "sink (water works) [kitchen]"))
        listOfEveryTask.add(BabyTask(i, "sink (disposal works) [kitchen]"))
        listOfEveryTask.add(BabyTask(i, "dining lights [kitchen]"))
        listOfEveryTask.add(BabyTask(i, "fridge (no damages) [kitchen]"))

    }

}