package com.example.composestudy.ui.main

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.composestudy.domain.model.Todo
import com.example.composestudy.domain.repository.TodoRepository
import kotlinx.coroutines.launch

//viewmodel에는 Data 조작을 위해 TodoRepository를 받도록 한다.
class MainViewModel(
    application: Application,
    private val todoRepository: TodoRepository
) : AndroidViewModel(application) {

    private val _items = mutableStateOf(emptyList<Todo>())
    val items : State<List<Todo>> = _items

    //이 부분 강의에서 생략된듯?
    init {
        viewModelScope.launch {
            todoRepository.obserbTodos()
                .collect { todos ->
                    _items.value = todos
                }
        }
    }

    private var recentlyDeleteTodo : Todo? = null

    fun addTodo(text : String){
        viewModelScope.launch {
            todoRepository.addTodo(Todo(title = text))
        }
    }

    fun toggle(uid : Int) {
        val todo = _items.value.find { todo -> todo.uid == uid }
        todo?.let {
            viewModelScope.launch {
                todoRepository.updateTodo(it.copy(isDone = !it.isDone).apply {
                    //uid는 copy가 안되서
                    this.uid = it.uid
                })
            }
        }
    }

    fun delete(uid : Int) {
        val todo = _items.value.find{ todo -> todo.uid == uid }
        todo?.let {
            viewModelScope.launch {
                todoRepository.deleteTodo(it)
                recentlyDeleteTodo = it
            }
        }
    }

    fun restoreTodo(){
        viewModelScope.launch {
            //recentlyDeleteTodo가 null이면 ?:에 의하여 launch를 취소한다.
            todoRepository.addTodo(recentlyDeleteTodo ?: return@launch)
            recentlyDeleteTodo = null
        }
    }
}