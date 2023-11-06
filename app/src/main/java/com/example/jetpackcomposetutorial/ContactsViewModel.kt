package com.example.jetpackcomposetutorial

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ContactsViewModel: ViewModel() {

    var id by mutableStateOf(1)

    var currentUnit by mutableStateOf(1)


    fun incrementId(){
        id += 1
    }

    fun decrementId(){
        id -= 1
    }



}