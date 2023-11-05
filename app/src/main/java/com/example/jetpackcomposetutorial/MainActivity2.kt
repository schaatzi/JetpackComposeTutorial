package com.example.jetpackcomposetutorial

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.state
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.jetpackcomposetutorial.ui.theme.JetpackComposeTutorialTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var id by remember {
                mutableStateOf(1)
            }
            var currentUnit by remember {
                mutableStateOf(listOfEveryTask.get(id).unitNum)
            }
            val scrollState = rememberScrollState()

            var firstIdOfUnit = 0
            var lastIdOfUnit = 0
            var tasksPerUnit = 0

            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxSize()
                    .padding(20.dp)

            ) {
//currentUnit-1)* listOfEveryTask.count()/30 + 1..(currentUnit-1)* listOfEveryTask.count() + listOfEveryTask.count()/30
                tasksPerUnit = listOfEveryTask.count()/36
                firstIdOfUnit = 1 +             tasksPerUnit*(currentUnit - 1)
                lastIdOfUnit = tasksPerUnit +   tasksPerUnit*(currentUnit - 1)

                for (i in firstIdOfUnit..lastIdOfUnit){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(10.dp),
                        text = "[ ${listOfEveryTask.get(id).complete} ] ${listOfEveryTask.get(i).details}",
                        fontSize = 30.sp,
                        style = LocalTextStyle.current.copy(lineHeight = 50.sp)

                    )

                    Button(onClick = {//go to list
                        id = i
                        val navigate = Intent(this@MainActivity2, MainActivity::class.java)
                        startActivity(navigate)
                    },
                        modifier = Modifier.padding(5.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)
                    ) {
                        Text(text = "Go to list    <", fontSize = 20.sp)
                    }
                }
            }

            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                val itemCount = listOfEveryTask.count()/36
                items(itemCount){

                }
            }
        }
    }
}



@Composable
fun Greeting2() {


}



@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    JetpackComposeTutorialTheme {
        Greeting2()
    }
}