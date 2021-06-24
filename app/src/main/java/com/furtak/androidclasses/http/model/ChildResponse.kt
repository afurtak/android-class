package com.furtak.androidclasses.http.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChildResponse(
    val name: String,
    val surname: String,
    val phone: String,
)