package com.example.shivam.audiobox

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shivam.audiobox.ui.theme.AudioBoxTheme
import java.util.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudioBoxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MockLogin()
                }
            }
        }
    }

}
@Composable
fun MockLogin() {
    var roomID by remember {

        mutableStateOf("")
    }
    var username by remember {

        mutableStateOf("")
    }
    var context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "audio box", fontSize = 32.sp, fontFamily = FontFamily.Monospace)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = roomID, onValueChange = {
            roomID = it
        }, label = { Text(text = "Room Id") })
        OutlinedTextField(value = username, onValueChange = {
            username = it
        }, label = { Text(text = "Username") })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
           roomID = generateRoomId()
            val intent = Intent(context,LiveAudioRoomActivity::class.java)
            intent.putExtra("userId",username)
            intent.putExtra("roomId",roomID)
            intent.putExtra("isHost",true)
            context.startActivity(intent)


        }) {
            Text(text = "Start Audiobox")
        }
        Button(onClick = {
            val intent = Intent(context,LiveAudioRoomActivity::class.java)
            intent.putExtra("userId",username)
            intent.putExtra("roomId",roomID)
            intent.putExtra("isHost",false)
            context.startActivity(intent)


        }) {
            Text(text = " Join Audiobox")
        }
    }
}
fun generateRoomId():String
{
    var id = StringBuilder()
    while (id.length<5)
    {
        var random = Random().nextInt(10)//45897
        id.append(random)
    }
    return id.toString()
}
