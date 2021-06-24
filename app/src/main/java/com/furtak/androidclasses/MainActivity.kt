package com.furtak.androidclasses

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.furtak.androidclasses.service.LocationService


class MainActivity : AppCompatActivity() {
    private lateinit var broadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startService(
            Intent(
                applicationContext,
                LocationService::class.java,
            ),
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(
            Intent(
                applicationContext,
                LocationService::class.java,
            ),
        )
    }
}