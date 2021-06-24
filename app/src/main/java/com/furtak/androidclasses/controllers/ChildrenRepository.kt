package com.furtak.androidclasses.controllers

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import com.furtak.androidclasses.model.Child
import java.io.File

interface ChildrenRepository {
    fun addChild(child: Child)

    fun getAllChildren(): LiveData<List<Child>>

    fun saveChildPhoto(bitmap: Bitmap): File
}