package com.furtak.androidclasses

import android.app.Application
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.furtak.androidclasses.di.mainModule
import com.furtak.androidclasses.workers.SynchronizationWorker
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupDependencyInjection()
        setupWorkers()
    }

    private fun setupDependencyInjection() {
        startKoin {
            androidContext(this@MyApplication)
            modules(
                mainModule,
            )
        }
    }

    private fun setupWorkers() {
        val synchronizationRequest: WorkRequest =
            OneTimeWorkRequestBuilder<SynchronizationWorker>()
                .build()

        WorkManager
            .getInstance(this)
            .enqueue(synchronizationRequest)
    }
}