package com.example.jetpackcomposetutorial

import android.content.Intent
//import android.graphics.Color
import android.os.Bundle
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposetutorial.ui.theme.JetpackComposeTutorialTheme




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "MainScreen"){
                composable(route = "MainScreen"){
                    MainScreen(navController)
                }
                composable(route = "DetailScreen"){

                }


            }

            CreateAllTasks()

            var id by remember {
                mutableStateOf(1)
            }
            var currentUnit by remember {
                mutableStateOf(listOfEveryTask.get(id).unitNum)
            }

            Column (
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ){
                Text(//previous task text
                    text = "[ ${listOfEveryTask.get(id - 1).complete} ] ${listOfEveryTask.get(id - 1).details}",
                    fontSize = 15.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(20.dp),
                    style = LocalTextStyle.current.copy(lineHeight = 25.sp)
                )

                Text(//current task text//////////////////////////////////////////////////////////
                    text = "[ ${listOfEveryTask.get(id).complete} ]  ${listOfEveryTask.get(id).details}",
                    fontSize = 30.sp,
                    modifier = Modifier.align(Alignment.Start),
                    style = LocalTextStyle.current.copy(lineHeight = 50.sp)
                )////////////////////////////////////////////////////////////////////////////////////

                Button(onClick = {//check and go to next
                    listOfEveryTask.get(id).complete = "X"
                    if(listOfEveryTask.get(id).unitNum == listOfEveryTask.get(id+1).unitNum){
                        id = id + 1
                    }
                    },
                    modifier = Modifier.padding(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
                ) {
                    Text(text = "Check + go to next", fontSize = 30.sp)
                }

                Button(onClick = {//check
                    listOfEveryTask.get(id).complete = "X"
                    if(listOfEveryTask.get(id).unitNum == listOfEveryTask.get(id+1).unitNum){
                        id = id + 1
                        id = id -1
                    } else {
                        id = id - 1
                        id = id + 1
                    }
                },
                    modifier = Modifier.padding(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text(text = "Check   [X]", fontSize = 20.sp)
                }

                Button(onClick = {//uncheck
                    listOfEveryTask.get(id).complete = "  "
                    if(listOfEveryTask.get(id).unitNum == listOfEveryTask.get(id+1).unitNum){
                        id = id + 1
                        id = id -1
                    } else {
                        id = id - 1
                        id = id + 1
                    }
                },
                    modifier = Modifier.padding(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text(text = "Uncheck   [ ]", fontSize = 20.sp)
                }

                Button(onClick = {//go to previous
                    if(listOfEveryTask.get(id).unitNum == listOfEveryTask.get(id - 1).unitNum && id > 1) {
                        id = id - 1
                    }
                    },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(text = "Go to previous    ^", fontSize = 20.sp)
                }

                Button(onClick = {//go to next
                    if(listOfEveryTask.get(id).unitNum == listOfEveryTask.get(id + 1).unitNum) {
                        id = id + 1
                    }

                },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(text = "Go to next    v", fontSize = 20.sp)
                }

                Button(onClick = {//go to list
                    val navigate = Intent(this@MainActivity, MainActivity2::class.java)
                    startActivity(navigate)
                },
                    modifier = Modifier.padding(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)
                ) {
                    Text(text = "Go to list    <", fontSize = 20.sp)
                }

                Text(
                    text = "Unit: ${TranslateUnitNumber(listOfEveryTask.get(id).unitNum)}  id: $id   total count: ${listOfEveryTask.count()}",
                    fontSize = 30.sp,
                    modifier = Modifier.align(Alignment.Start)
                )





            }
            /*
            JetpackComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    Home()
                }
            }
            */
        }
    }

}

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



@Preview(showBackground = true)         //text under @Preview will show up on the right after clicking on Split view
@Composable
fun GreetingPreview() {                 //use the GreetingPreview function to put things in that we want to see?
    JetpackComposeTutorialTheme {       //naming GreetingPreview to something else doesnt create errors though?
        Greeting("Andro")
        Home()
    }
}