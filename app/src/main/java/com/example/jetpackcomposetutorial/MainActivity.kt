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
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposetutorial.ui.theme.JetpackComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var id by remember {
                mutableStateOf(1)
            }
            var checkmark by remember {
                mutableStateOf("")
            }

            //var idMod = baseList.get(id).unitNum * (baseList.count()-1)

            Column (
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ){
                Text(//previous task text
                    text = "[ ${baseList.get(id - 1).complete} ] ${baseList.get(id - 1).details}",
                    fontSize = 15.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(20.dp),
                    style = LocalTextStyle.current.copy(lineHeight = 25.sp)
                )
                Text(//current task text
                    text = "[ ${baseList.get(id).complete} ]${baseList.get(id).details}",
                    fontSize = 30.sp,
                    modifier = Modifier.align(Alignment.Start),
                    style = LocalTextStyle.current.copy(lineHeight = 50.sp)
                )

                Button(onClick = {//checked
                    if(baseList.get(id).unitNum == baseList.get(id+1).unitNum){
                        baseList.get(id).complete = "X"
                        id = id + 1}

                    },
                    modifier = Modifier.padding(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
                ) {
                    Text(text = "Check", fontSize = 50.sp)
                }

                Button(onClick = {//uncheck
                    },
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(text = "Uncheck", fontSize = 20.sp)
                }

                Button(onClick = {//go to building
                    val navigate = Intent(this@MainActivity, MainActivity2::class.java)
                    startActivity(navigate)
                },
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(text = "Go to building", fontSize = 20.sp)
                }

                Text(
                    text = "Unit: ${task1.unitNum}",
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

@Composable
fun Greeting(name: String) {

    val mudPan = 15

    Column (
        verticalArrangement = Arrangement.Top,
        //horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            //.size(200.dp)
            //.background(androidx.compose.ui.graphics.Color.White)
    )
    {

    for (i in 1..4) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = null
        )
    }
        Text(
            text = "Hello $name!",
            color = androidx.compose.ui.graphics.Color.Blue,
            modifier = Modifier
                .background(androidx.compose.ui.graphics.Color.LightGray)
        )

        Text(
            text = "wowza $mudPan",
            color = androidx.compose.ui.graphics.Color.Blue,
            modifier = Modifier
                .background(androidx.compose.ui.graphics.Color.LightGray)
        )

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