package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import java.time.Period
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uploadWorkRequest : WorkRequest =
            PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES)
                .build()

        WorkManager
            .getInstance(this)
            .enqueue(uploadWorkRequest)

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            WorkManager
                .getInstance(this)
                .enqueue(uploadWorkRequest)
        }
    }
}