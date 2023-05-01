package com.example.simplelogin

import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.sql.Timestamp


open class FileLogger {

    open fun printLogs(logs:String){

        Log.d("DEBUG",logs)
//        System.out.println(logs)
    }

    open fun logError(error:String){
        val file = File("errors.txt")
//        val fos: FileOutputStream = FileOutputStream(File("errors.txt"))
        file.appendText(
            text = error
        )
    }
}

class CustomFileLogger:FileLogger(){
    override fun logError(error: String) {
        try {
            val file = File("errors.txt")
            val currentTimeMillis = System.currentTimeMillis()
            val timeStamp = Timestamp(currentTimeMillis).toString()
            file.appendText(
                text = error
            )
            file.appendText(
                timeStamp
            )
        }catch(e:Exception){
            println(e)
        }
    }
}

