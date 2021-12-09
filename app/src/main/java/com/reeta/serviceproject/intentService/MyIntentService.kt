package com.reeta.serviceproject.intentService

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL

//what we are passing in intentService constructor it is thread name
class MyIntentService : IntentService("MyIntentService") {

    var TAG="intentService"

    //this will run in main thread
    override fun onCreate() {
        super.onCreate()
        Log.v(TAG,"onCreate ${Thread.currentThread().name}")
    }
    //it will run in background and compalsary to override ths method
    override fun onHandleIntent(intent: Intent?) {
       Log.v(TAG,"onHandlerIntent ${Thread.currentThread().name}")
        downloadFile()
    }

    //onDestry will called automatically called as service finished there work.it also run run in main thread
    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG,"onDestroy ${Thread.currentThread().name}")
    }

    private fun downloadFile(){

        try {
            var directory=File(filesDir,File.separator+"vogella")
            if (!directory.exists()){
                directory.mkdir()
            }
            var file:File=File(directory,"reeta.html")
            if (!file.exists()){
                file.createNewFile()
            }
            var url=URL("https://www.vogella.com/index.html")
            var inputStream=url.openConnection().getInputStream()
            var reader=InputStreamReader(inputStream)
            var writer=FileOutputStream(file,true)
            var data:Int=reader.read()

            //when it will reach -1 that means it is end of file
            while (data!=-1){
                var ch:Char=data as Char
                writer.write(data)
                data = reader.read()
            }

            var fileInputStream=FileInputStream(file)
            var fileData=fileInputStream.read()
            var string:StringBuffer= StringBuffer()
            while (fileData!=-1){
                var ch :Char=fileData as Char
                string = string.append(ch)
                fileData=fileInputStream.read()
            }
            Log.v(TAG, string.toString())


        }catch (e:Exception){

        }

    }


}