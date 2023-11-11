//firebase id: checklist-c3fd0
package com.example.jetpackcomposetutorial

import android.content.Intent
//import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.ModifierLocal
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposetutorial.ui.theme.JetpackComposeTutorialTheme
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database


val viewModel = ContactsViewModel()
val listOfEveryTask = mutableListOf<BabyTask>()

var databaseGet = FirebaseDatabase.getInstance().reference
var database = Firebase.database

class MainActivity : ComponentActivity() {


    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ResetAllAppTasks()
        //WriteAllToDatabase()


        setContent {

            PullAllFromDatabase()

            navController = rememberNavController()
            SetupNavGraph(navController = navController)

        }
    }


}

/*
@Composable
fun Home(){
    var count by remember {
        mutableStateOf(0)
    }
    var flipFlop by remember {
        mutableStateOf(0)
    }
    var checkmark = "  "
    var id = 2

    Column (
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ){
        Text(
            text = "$id [$checkmark]   previous task description akjndf kajndfjngjfnjng fjnd djnjnfg",
            fontSize = 15.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(20.dp),
            style = LocalTextStyle.current.copy(lineHeight = 25.sp)
        )
        Text(
            text = "$id [$checkmark]   task description akjndf kajndfjngjfnjng fjnd djnjnfg",
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.Start),
            style = LocalTextStyle.current.copy(lineHeight = 50.sp)
        )

        Button(onClick = {

        }) {
            Text(text = "Navigate", fontSize = 18.sp)
        }

    }
}
*/




