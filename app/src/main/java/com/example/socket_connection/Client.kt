package com.example.socket_connection
import android.os.Handler
import android.os.Looper
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket
import java.util.concurrent.Executors

class Client(hostAddress : InetAddress ) :Thread(){
    var address :String = hostAddress.hostAddress
    private lateinit var inputStream : InputStream
    private lateinit var outputStream: OutputStream
    private lateinit var socket : Socket

    fun write(byteArray: ByteArray){
        try {
            outputStream.write(byteArray)
        }catch (e : IOException){
            e.printStackTrace()
        }
    }

    override fun run() {
        val executor = Executors.newSingleThreadExecutor()
        var handler = Handler(Looper.getMainLooper())
        try {
            socket = Socket()
            socket.connect(InetSocketAddress(address,8888),500)
            inputStream = socket.getInputStream()
            outputStream = socket.getOutputStream()
        }catch (e : IOException){
            e.printStackTrace()
        }
        executor.execute(Runnable {
            kotlin.run {
                val buffer = ByteArray(1024)
                //var byte : Int
                while (true){
                    try {
                        var byte = inputStream.read(buffer)
                        if(byte > 0){
                            var finalByte = byte
                            handler.post(Runnable {
                                kotlin.run {
                                    Log.i("Server class","${String(buffer,0,finalByte)}")
                                }
                            })
                        }
                    }catch (e : IOException){
                        e.printStackTrace()
                    }
                }
            }
        })
    }
}