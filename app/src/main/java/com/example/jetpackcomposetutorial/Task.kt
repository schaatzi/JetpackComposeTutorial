package com.example.jetpackcomposetutorial

import android.text.BoringLayout

/*
class Task (var taskId: Int,  var room: String,  var unitNum: Int,  var needSub: String,  var complete: String,  var concreteItem: String,  var details: String){

    var notesForSub = ""

}
*/



open class ParentTask (

){
    var taskId: Int = 0
    var needSub: String = "  "
    var notes: String = ""
    var complete: String = "  "

}