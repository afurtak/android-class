package com.furtak.androidclasses.service

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.telephony.SmsManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.furtak.androidclasses.*
import kotlin.random.Random


class LocationService : Service() {
    private var prevDistance = 0f

    private lateinit var listener: LocationListener
    private lateinit var locationManager: LocationManager
    private lateinit var notificationManager: NotificationManager

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @SuppressLint("MissingPermission")
    override fun onCreate() {
        listener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                val currentDistance = location.distanceTo(kindergartenLocation)

                if (currentDistance > firstZoneRadius && prevDistance <= firstZoneRadius) {
                    showNotification()
                }

                if (currentDistance > secondZoneRadius && prevDistance <= secondZoneRadius) {
                    sendSMS()
                }

                prevDistance = currentDistance
            }

            override fun onStatusChanged(s: String, i: Int, bundle: Bundle) {}
            override fun onProviderEnabled(s: String) {}
            override fun onProviderDisabled(s: String) {}
        }

        locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            3000,
            0f,
            listener,
        )

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel()
        showNotification()
    }

    override fun onDestroy() {
        super.onDestroy()
        locationManager.removeUpdates(listener)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Channel name"
            val descriptionText = "Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel("CHANNEL_ID", name, importance)
                .apply {
                    description = descriptionText
                }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager
                .createNotificationChannel(channel)
        }
    }

    private fun showNotification() {
        val intent = Intent(this, LocationService::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getService(
            this, 0, intent, 0)

        val builder = NotificationCompat.Builder(this, "CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_baseline_warning_24)
            .setContentTitle("Alert")
            .setContentText("Alert, you are outside the zone 1")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(Random.nextInt(), builder.build())
        }
    }

    private fun sendSMS() {
        val smsManager: SmsManager = SmsManager.getDefault()

        smsManager.sendTextMessage(
            phoneNumber,
            null,
            "child outside the zone 2",
            null,
            null,
        )
    }
}