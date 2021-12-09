package com.reeta.serviceproject.normalService

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.reeta.serviceproject.R
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

class NormalService(): Service()  {

    val  TAG="serviceTag"
    var name:String=""

    //it will
    override fun onCreate() {
        super.onCreate()
        displayNotification("Service Start","running service")
        Log.v(TAG,"Oncreate in service")
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    //sometime andoird kills service in this case if service will create again intent data will not restore it can we null
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (intent!=null){
            name= intent.getStringExtra("name").toString()
        }
        Log.v(TAG,"onStartCommand ${Thread.currentThread().name}")
        lanunchBackgoundThread()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun lanunchBackgoundThread() {
        val thread=Thread(runnable)
        thread.start()
    }

    private val runnable= Runnable {
        saveFile()
    }

    private fun saveFile(){
        //create a directory or folder

        try {
            val directory:File=File(filesDir, File.separator+"nameFloder")

            //if directory not exit then create it
            if (!directory.exists()){
                directory.mkdir()
            }

            // create a file inside a directory or folder
            var file:File=File(directory,"name.text")
            if (!file.exists()){
                file.createNewFile()
            }

            //open above file and write
            var fileOutStream:FileOutputStream= FileOutputStream(file,true)
            var writer:OutputStreamWriter=OutputStreamWriter(fileOutStream)
            writer.append(name+"\n")
            writer.close()

        }catch (e:Exception){

        }

    }


    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    // for showing notification
    private fun displayNotification(title: String, task: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "masai",
                "masai",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        val notification: NotificationCompat.Builder =
            NotificationCompat.Builder(applicationContext, "masai")
                .setContentTitle(title)
                .setContentText(task)
                .setSmallIcon(R.mipmap.ic_launcher)
        notificationManager.notify(1, notification.build())
    }

}