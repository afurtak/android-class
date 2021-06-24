package com.furtak.androidclasses.storage.model

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import java.lang.IllegalArgumentException

@JsonClass(generateAdapter = true)
data class Child(
    val id: Long,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val photoPath: String?,
) {
    fun toJson(): String =
        moshiAdapter.toJson(this)

    companion object {
        private val moshiAdapter = Moshi
            .Builder()
            .build()
            .adapter(Child::class.java)

        fun fromJson(json: String): Child =
            moshiAdapter.fromJson(json)
                ?: throw IllegalArgumentException("Given string is not valid Child json")
    }
}