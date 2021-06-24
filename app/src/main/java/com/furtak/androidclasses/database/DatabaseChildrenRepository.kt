package com.furtak.androidclasses.database

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.furtak.androidclasses.controllers.ChildrenRepository
import com.furtak.androidclasses.database.model.ChildEntity
import com.furtak.androidclasses.model.Child
import com.furtak.androidclasses.utilities.DTOConverter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.*

class DatabaseChildrenRepository(
    private val context: Context,
    private val database: AppDatabase,
    private val entityConverter: DTOConverter<Child, ChildEntity>,
) : ChildrenRepository{
    override fun addChild(child: Child) {
        database
            .getChildDao()
            .insertChild(entityConverter.toDto(child))
    }

    override fun getAllChildren(): LiveData<List<Child>> {
        return database
            .getChildDao()
            .getAllChildren()
            .map(entityConverter::fromDto)
    }

    override fun saveChildPhoto(bitmap: Bitmap): File {
        val filesDir = context.filesDir
        val uuid = UUID.randomUUID()
        val imageFile = File(filesDir, "$uuid.jpg")

        FileOutputStream(imageFile).use { out ->
            bitmap.compress(
                Bitmap.CompressFormat.JPEG,
                70,
                out
            )
        }

        return imageFile
    }
}