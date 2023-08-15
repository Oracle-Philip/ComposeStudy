package com.example.composestudy.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composestudy.domain.model.Todo

@Database(entities = arrayOf(Todo::class), version = 1)
abstract class TodoDatabase : RoomDatabase(){
    abstract fun todoDao() : TodoDao
}