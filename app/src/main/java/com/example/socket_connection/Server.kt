package com.example.socket_connection
import android.os.Handler
import android.os.Looper
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.Executors

class Server() : Thread() {
    private lateinit var serverSocket: ServerSocket
    private lateinit var inputStream: InputStream
    private lateinit var outputStream: OutputStream
    private lateinit var socket: Socket
    override fun run() {
        val executors = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        try {
            serverSocket = ServerSocket(8888)
            socket = serverSocket.accept()
            inputStream =socket.getInputStream()
            outputStream = socket.getOutputStream()
        }catch (ex: IOException){
            ex.printStackTrace()
        }
        executors.execute(Runnable{
            kotlin.run {
                val buffer = ByteArray(1024)
                var byte:Int
                while (true){
                    try {
                        byte =  inputStream.read(buffer)
                        if(byte > 0){
                            var finalByte = byte
                            handler.post(Runnable{
                                kotlin.run {
                                    var tmpMeassage = String(buffer,0,finalByte)

                                    Log.i("Server class","$tmpMeassage")
                                }
                            })

                        }
                    }catch (ex:IOException){
                        ex.printStackTrace()
                    }
                }
            }
        })
    }

    fun write(byteArray: ByteArray){
        try {
            Log.i("Server write","$byteArray sending")
            outputStream.write(byteArray)
        }catch (ex:IOException){
            ex.printStackTrace()
        }
    }
}