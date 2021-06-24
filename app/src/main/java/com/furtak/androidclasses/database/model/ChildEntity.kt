package com.furtak.androidclasses.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Child",
)
data class ChildEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val photoPath: String?,
)