package com.example.jetpackcomposetutorial

fun literalNullNotesToBlank(idMod: Int, notesIdMod: Int): String{
    var blankOrNotes: String? = ""
    blankOrNotes = (pullNotes(notesIdMod) ?: "").toString()
    println("blankOrNotes: $blankOrNotes")
    if (blankOrNotes == "null"){
        println("it's a string called null!!!")
        blankOrNotes = ""
    }
    return blankOrNotes
}