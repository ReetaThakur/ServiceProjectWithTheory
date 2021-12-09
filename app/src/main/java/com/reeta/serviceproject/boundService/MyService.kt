package com.reeta.serviceproject.boundService

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import androidx.core.app.JobIntentService
import com.reeta.serviceproject.R

class MyService : Service() {

    var mediaPlayer:MediaPlayer=MediaPlayer()

    override fun onCreate() {
        super.onCreate()
        mediaPlayer=MediaPlayer.create(this, R.raw.song)
    }

    class ServiceBinder(): Binder() {
        fun getMyService():MyService{
            return MyService()
        }
    }


    // IBinder is a interface we can use this interface and communicate activity and service
    //IBinder give service object
    override fun onBind(intent: Intent): IBinder {
        return ServiceBinder()
    }

    fun play(){
        if (!mediaPlayer.isPlaying){
            mediaPlayer.start()
        }
    }

    fun pause(){
        if (!mediaPlayer.isPlaying){
            mediaPlayer.pause()
        }

    }
    fun stop(){
        if (mediaPlayer.isPlaying){
            mediaPlayer.stop()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

}