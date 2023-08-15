package com.example.composestudy.domain.repository

import com.example.composestudy.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun obserbTodos() : Flow<List<Todo>>

    suspend fun addTodo(todo : Todo)

    suspend fun updateTodo(todo : Todo)

    suspend fun deleteTodo(todo : Todo)
}