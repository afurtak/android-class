package com.furtak.androidclasses.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.furtak.androidclasses.database.model.ChildEntity

@Dao
interface ChildDao {
    @Insert
    fun insertChild(child: ChildEntity): Long

    @Query("select * from Child")
    fun getAllChildren(): LiveData<List<ChildEntity>>
}