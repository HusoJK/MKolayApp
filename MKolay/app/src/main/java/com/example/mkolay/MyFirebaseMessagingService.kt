package com.example.mkolay

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.media.app.NotificationCompat
import com.example.mkolay.Activities.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val channelId = "notification_chanel"
const val channelName = "package com.example.mkolay"

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        if(remoteMessage.notification != null){
            generateNotification(remoteMessage.notification!!.title!!,
                remoteMessage.notification!!.body!!
            )
        }

    }

    fun getRemoteView(title:String, message: String) :RemoteViews {
        val remoteView = RemoteViews("package com.example.mkolay",R.layout.notification)

        remoteView.setTextViewText(R.id.title,title)
        remoteView.setTextViewText(R.id.message,message)
        remoteView.setImageViewResource(R.id.notification,R.drawable.ic_stat_ic_notification)
        return remoteView
    }
    fun generateNotification(title: String,message:String){
      val intent = Intent(this,MainActivity::class.java)
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

      val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT)

      var builder : androidx.core.app.NotificationCompat.Builder =androidx.core.app.NotificationCompat.Builder(applicationContext,
          channelId).setSmallIcon(R.drawable.ic_mkolay_icon)
          .setAutoCancel(true)
          .setVibrate(longArrayOf(1000,1000,1000,1000))
          .setOnlyAlertOnce(true)
          .setContentIntent(pendingIntent)

      builder = builder.setContent(getRemoteView(title,message))

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(channelId, channelName,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(0,builder.build())
  }
}