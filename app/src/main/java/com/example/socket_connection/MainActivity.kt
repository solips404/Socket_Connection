package com.example.socket_connection
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*CoroutineScope(IO).launch{
            val socket = Socket("192.168.56.1", 9999)
            while (true) {
                var text = BufferedReader(InputStreamReader(socket.inputStream)).readLine()
                // if text is not null
                // Toast.makeText(this@MainActivity, "Set IP", Toast.LENGTH_SHORT).show()
            }
        }*/
        /*suspend fun sendMessage(message:String){
            val socket = Socket("192.168.56.1", 9999)
            socket.outputStream.write(message.toByteArray())
            socket.close()
        }*/

        findViewById<Button>(R.id.button).setOnClickListener {
            Toast.makeText(this@MainActivity, "Set IP", Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            /*CoroutineScope(IO).launch {
                Log.d("TAG", "message")
                //sendMessage("record")
            }*/
        }
    }
}