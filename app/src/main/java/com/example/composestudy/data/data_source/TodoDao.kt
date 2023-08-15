package com.example.composestudy.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.example.composestudy.domain.model.Todo
import kotlinx.coroutines.flow.Flow

//https://issuetracker.google.com/issues/236612358?hl=ko&pli=1
/**
 * Room 2.5.2
Kotlin 1.9
KSP 1.9.0-1.0.11

 */
@Dao
interface TodoDao {
    //flow는 비동기적인 데이터처리에 적합하다.
    @Query("SELECT * FROM todo ORDER BY date DESC")
    fun todos() : Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo : Todo)

    @Update
    suspend fun update(todo : Todo)

    @Delete
    suspend fun delete(todo : Todo)
}