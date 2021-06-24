package com.furtak.androidclasses.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furtak.androidclasses.controllers.ChildrenRepository
import com.furtak.androidclasses.model.Child
import java.io.File
import java.io.FileOutputStream
import java.util.*

class ChildInputFormViewModel(
    val childrenRepository: ChildrenRepository,
) : ViewModel() {
    private var imageFile: File? = null
        set(value) {
            field = value
            if (field != null) {
                _imageBitmap.value = field!!.absolutePath
            } else {
                _imageBitmap.value = null
            }
        }

    private val _imageBitmap = MutableLiveData<String>()
        .also {
            it.value = null
        }

    val imageBitmap: LiveData<String>
        get() = _imageBitmap

    private val _name = MutableLiveData<String>()
        .also { it.value = "" }

    val name: LiveData<String>
        get() = _name

    fun onNameChange(newValue: String) {
        _name.value = newValue
    }

    private val _surname = MutableLiveData<String>()
        .also { it.value = "" }

    val surname: LiveData<String>
        get() = _surname

    fun onSurnameChange(newValue: String) {
        _surname.value = newValue
    }

    private val _phone = MutableLiveData<String>()
        .also { it.value = "" }

    val phone: LiveData<String>
        get() = _phone

    fun onPhoneChange(newValue: String) {
        _phone.value = newValue
    }

    fun savePhoto(filesDir: File, bitmap: Bitmap) {
        val uuid = UUID.randomUUID()
        imageFile = File(filesDir, "$uuid.jpg")

        FileOutputStream(imageFile).use { out ->
            bitmap.compress(
                Bitmap.CompressFormat.JPEG,
                70,
                out
            )
        }
    }

    fun saveChild(): Boolean {
        val name = name.value ?: return false
        val surname = surname.value ?: return false
        val phone = phone.value ?: return false

        val child = Child(
            name = name,
            surname = surname,
            phoneNumber = phone,
            photoPath = imageFile?.absolutePath,
        )

        childrenRepository.addChild(child)

        imageFile = null

        return true
    }

    fun clearForm() {
        imageFile = null
        _name.value = ""
        _surname.value = ""
        _phone.value = ""
    }

    override fun onCleared() {
        super.onCleared()
        imageFile?.delete()
    }

    fun showError() {
        // TODO implement this
    }
}