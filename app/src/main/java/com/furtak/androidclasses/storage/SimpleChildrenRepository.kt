package com.furtak.androidclasses.storage

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.furtak.androidclasses.controllers.ChildrenRepository
import com.furtak.androidclasses.model.Child
import com.furtak.androidclasses.utilities.DTOConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types.newParameterizedType
import com.furtak.androidclasses.storage.model.Child as ChildDTO
import java.io.File
import java.io.FileOutputStream
import java.lang.IllegalStateException
import java.util.*

class SimpleChildrenRepository(
    private val filesDir: File,
    private val converter: DTOConverter<Child, ChildDTO>,
) : ChildrenRepository {
    private val childrenLive: MutableLiveData<List<Child>> =
        MutableLiveData<List<Child>>()
            .also {
                it.postValue(listOf())
            }

    override fun addChild(child: Child) {
        val listFile = File(filesDir, "children.json")
        val children = getAllChildrenList() + listOf(child)
        val toSave = converter.toDto(children)
        childrenLive.postValue(children)
        listFile.delete()
        listFile.createNewFile()
        listFile.writeText(jsonAdapter.toJson(toSave))
    }

    override fun getAllChildren(): LiveData<List<Child>> {
        childrenLive.postValue(getAllChildrenList())
        return childrenLive
    }

    override fun saveChildPhoto(bitmap: Bitmap): File {
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

    private fun getAllChildrenList():List<Child> {
        val listFile = File(filesDir, "children.json")
        return if (listFile.exists()) {
            val childrenList = jsonAdapter
                .fromJson(listFile.readText())
                ?: throw IllegalStateException(
                    "children.json is not proper json file with list of children"
                )

            return converter.fromDto(childrenList)
        }
        else
            listOf()

    }

    companion object {
        private val childrenList = newParameterizedType(
            List::class.java,
            ChildDTO::class.java,
        )

        private val jsonAdapter = Moshi
            .Builder()
            .build()
            .adapter<List<ChildDTO>>(childrenList)
    }
}