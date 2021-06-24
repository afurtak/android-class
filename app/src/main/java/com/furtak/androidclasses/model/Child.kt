package com.furtak.androidclasses.model

import kotlin.random.Random

data class Child(
    val id: Long?,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val photoPath: String?,
) {
    constructor(
        name: String,
        surname: String,
        phoneNumber: String,
        photoPath: String?,
    ) : this(
        id = null,
        name = name,
        surname = surname,
        phoneNumber = phoneNumber,
        photoPath = photoPath,
    )
}