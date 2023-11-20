//firebase id: checklist-c3fd0
package com.example.jetpackcomposetutorial

import android.graphics.Color.parseColor
//import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposetutorial.StartupFunctions.InitialPull
import com.example.jetpackcomposetutorial.StartupFunctions.OnDatabaseChange
import com.example.jetpackcomposetutorial.StartupFunctions.ResetAllAppTasks
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database


val viewModel = ContactsViewModel()
val listOfEveryTask = mutableListOf<BabyTask>()

var databaseGet = FirebaseDatabase.getInstance().reference
var database = Firebase.database





val String.color
    get() = Color(parseColor(this))

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ResetAllAppTasks()      //leaving this on prevents crashes on startup
        //WriteAllToDatabase()
        InitialPull()
        OnDatabaseChange()

        println("main activity list count equals: ${listOfEveryTask.count()}") //324




        setContent {

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




