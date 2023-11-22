package com.example.jetpackcomposetutorial

fun literalNullNotesToBlank(IdMod: Int): String{
    var blankOrNotes: String? = ""
    blankOrNotes = (pullNotes(IdMod) ?: "").toString()
    println("blankOrNotes: $blankOrNotes")
    if (blankOrNotes == "null"){
        println("it's a string called null!!!")
        blankOrNotes = ""
    }
    return blankOrNotes
}

fun nullToBlank(literallyNullOrOther: String?): String?{
    var blank: String = ""
    if(literallyNullOrOther == "null"){
        return blank
    } else {
        return literallyNullOrOther
    }
}