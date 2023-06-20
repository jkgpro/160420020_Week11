package com.jeremy.a160420020_week4.view

import android.Manifest
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.jeremy.a160420020_week4.R
import com.jeremy.a160420020_week4.util.createNotifChannel

class MainActivity : AppCompatActivity() {
    init {
        instance = this
    }
    companion object {
        private var instance:MainActivity ?= null
        fun showNotif(title:String, content:String, icon:Int){
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"

            val builder = NotificationCompat.Builder(
                instance!!.applicationContext, channelId).apply {
                    setSmallIcon(icon)
                    setContentTitle(title)
                    setContentText(content)
                    setStyle(NotificationCompat.BigTextStyle())
                    priority = NotificationCompat.PRIORITY_DEFAULT
                    setAutoCancel(true)
            }

            val manager = NotificationManagerCompat.from(instance!!.applicationContext)
            if (ActivityCompat.checkSelfPermission(
                    instance!!.applicationContext,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            manager.notify(1001, builder.build())

        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotifChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
        getString(R.string.app_name), "App Notification Channel")
    }
}