package com.reeta.serviceproject.normalService

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.reeta.serviceproject.R

class MainActivity : AppCompatActivity() {

    lateinit var click:Button
    lateinit var name:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        click=findViewById(R.id.btnClick)
        name= findViewById(R.id.editText)
        click.setOnClickListener {
            val intent=Intent(this,NormalService::class.java)
            intent.putExtra("name",name.text.toString())
            startService(intent)
        }
    }


}



/*

Service :- A Service is an application component that can perform long-running operations in the background.
           It does not provide a user interface. Once started, a service might continue running for some
           time, even after the user switches to another application. services not attached to
           activity even we start service through activity
            For example, a service can handle network transactions, play music,
            perform file I/O, or interact with a content provider, all from the background.

     By default Service run in main Thread not in background thread and services do not have UI like activity and fragment.
      Every android component have life cycles so service also have life cycles.
      1) onCreate() :- when service is start this method is called like activity but it is not called again
                       and again. it called only once.The system invokes this method to perform one-time setup
                       procedures when the service is initially created (before it calls either onStartCommand()
                       or onBind()). If the service is already running, this method is not called.

     2) onDestroy() :- then service is destroy this method is called.The system invokes this method when the
                        service is no longer used and is being destroyed. Your service should implement
                        this to clean up any resources such as threads, registered listeners, or receivers.
                        This is the last call that the service receives.

     3) onStartCommand() :- The system invokes this method by calling startService() when another
                            component (such as an activity) requests that the service be started.
                            When this method executes, the service is started and can run in the
                            background indefinitely. If you implement this, it is your responsibility to
                            stop the service when its work is complete by calling stopSelf() or stopService().

     4) onBlind() :- The system invokes this method by calling bindService() when another component
                      wants to bind with the service (such as to perform RPC). In your implementation of
                      this method, you must provide an interface that clients use to communicate with the
                      service by returning an IBinder. You must always implement this method; however, if
                      you don't want to allow binding, you should return null.

  * Types of Services :- These are the three different types of services

  1) Foreground :- A foreground service performs some operation that is noticeable to the user. For example,
                   an audio app would use a foreground service to play an audio track. Foreground services
                   must display a Notification. Foreground services continue running even when the user
                   isn't interacting with the app.When you use a foreground service, you must display a
                   notification so that users are actively aware that the service is running.
                   This notification cannot be dismissed unless the service is either stopped or removed
                   from the foreground.

  2) Background :- A background service performs an operation that isn't directly noticed by the user.
                   For example, if an app used a service to compact its storage, that would usually be a
                   background service.

  3) Bound :- A service is bound when an application component binds to it by calling bindService().
               A bound service runs only as long as another application component is bound to it.
               Multiple components can bind to the service at once, but when all of them unbind,
               the service is destroyed.


    How to start service ??
    A started service is one that another component starts by calling startService(), which results in a
    call to the service's onStartCommand() method.
    When a service is started, it has a lifecycle that's independent of the component that started it.
    The service can run in the background indefinitely, even if the component that started it is destroyed.
    As such, the service should stop itself when its job is complete by calling stopSelf(), or another
    component can stop it by calling stopService().
    An application component such as an activity can start the service by calling startService() and
    passing an Intent that specifies the service and includes any data for the service to use. The service
    receives this Intent in the onStartCommand() method.

    The Service class is the base class for all services. When you extend this class, it's important to
    create a new thread in which the service can complete all of its work; the service uses your
    application's main thread by default, which can slow the performance of any activity that your
    application is running.



 */