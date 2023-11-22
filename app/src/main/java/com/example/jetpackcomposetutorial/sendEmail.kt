package com.example.jetpackcomposetutorial

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast



fun Context.sendMail(to: String, subject: String, body: String){
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, body)
        startActivity(intent)
    }
    catch (e: ActivityNotFoundException){
        //
    }
    catch (t: Throwable){
        //
    }
}