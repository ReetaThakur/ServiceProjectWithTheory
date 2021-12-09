package com.reeta.serviceproject.boundService

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import com.reeta.serviceproject.R

class MainActivity3 : AppCompatActivity(), View.OnClickListener {

     lateinit var play:Button
     lateinit var pause:Button
     lateinit var stop:Button
     lateinit var myService: MyService

    private val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
           var binder: MyService.ServiceBinder= service as MyService.ServiceBinder
            myService=binder.getMyService()
        }
        override fun onServiceDisconnected(name: ComponentName) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        play=findViewById(R.id.btnPlay)
        pause=findViewById(R.id.btnPause)
        stop=findViewById(R.id.btnStop)
        play.setOnClickListener(this)
        pause.setOnClickListener(this)
        stop.setOnClickListener(this)
        var intent=Intent(this,MyService::class.java)
        bindService(intent,serviceConnection, BIND_AUTO_CREATE)
    }

    override fun onClick(v: View?) {
        val id = v!!.id
        when (id) {
            R.id.btnPlay -> {
                myService.play()
            }
            R.id.btnPause ->{
                myService.pause()
            }
            R.id.btnStop ->{
                myService.stop()
            }
        }
    }
}


/*

Service :- We can categorize in 2 types
1) Bound service    2) Unbound service

1) Unbound service :- unbounded service will work till the completion even after
                      activity is destroyed.Unbound Service is started by calling
                      startService() method.


                    A service is started when an application component,
                    such as an activity, starts it by calling startService().

2) Bound service :- Bounded services are bounded to an activity which binds it and
                    will work only till bounded activity is alive.Bound Service is
                    started by calling bindService() method.

                    in this service it is attached with that componenet in which component
                    we start that service,once that activity or fragment will destroy
                    that service also destroy whether its work complete or not.


                     with bound service communiation is easy between componanets and
                    services. A service is bound when an application component binds to
                    it by calling bindService(). A bound service offers a client-server
                    interface that allows components to interact with the service,
                    send requests, get results, and even do so across processes with
                    interprocess communication




 # Life Span of a service:-
  Once an unboundService is started it runs indefinitely until
   1) Application component calls stopService() method
   2) Service itself calls SelfStop() method.

BoundService runs as long as the service is bound to a client. When there is no
active client bound with the service, the system destroys the Service
 */