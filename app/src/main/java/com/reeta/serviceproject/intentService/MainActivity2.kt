package com.reeta.serviceproject.intentService

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.reeta.serviceproject.R

class MainActivity2 : AppCompatActivity() {

    lateinit var download:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        download=findViewById(R.id.btndownload)
        download.setOnClickListener {
            val intent =Intent(this,MyIntentService::class.java)
            startService(intent)
        }
    }
}

/*
IntentService() :- IntentService is an extension of the Service component class that
                   handles asynchronous requests (expressed as Intents) on demand.
                   Clients send requests through Context.startService(Intent) calls;
                   the service is started as needed, handles each Intent in turn using
                   a worker thread, and stops itself when it runs out of work.
  All requests are handled on a single worker thread --
  they may take as long as necessary (and will not block the application's main loop),
  but only one request will be processed at a time.

onHandleIntent(Intent intent)
This method is invoked on the worker thread with a request to process.

JobIntentService():- it is same as IntentService ,it also extends service class internally
                      and by extending JobIntentService class it is must to override one
                      method that is - onHandleWork()
 onHandleWork():- Called serially for each work dispatched to and processed by the service.



 */