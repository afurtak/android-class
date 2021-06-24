package com.furtak.androidclasses.workers

import android.content.Context
import android.graphics.BitmapFactory
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.furtak.androidclasses.controllers.ChildrenRepository
import com.furtak.androidclasses.http.KindergartenService
import com.furtak.androidclasses.model.Child
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class SynchronizationWorker(
    appContext: Context,
    params: WorkerParameters,
) : CoroutineWorker(appContext, params), KoinComponent {

    override suspend fun doWork(): Result {
        service.getChildrenList().forEach { childResponse ->
            val response = service.getChildPhoto(childResponse.name.lowercase(Locale.getDefault()))
            val bitmap = BitmapFactory.decodeStream(response.byteStream())
            val bitmapFile = get<ChildrenRepository>()
                .saveChildPhoto(bitmap)

            val child = Child(
                childResponse.name,
                childResponse.surname,
                childResponse.phone,
                bitmapFile.absolutePath,
            )

            get<ChildrenRepository>()
                .addChild(child)
        }

        return Result.success()
    }

    companion object {
        val service = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(KindergartenService::class.java)
    }
}