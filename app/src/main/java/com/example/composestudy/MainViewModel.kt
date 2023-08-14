package com.example.composestudy

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val url = mutableStateOf("https://www.google.com")

    private val _undoSharedFlow = MutableSharedFlow<Boolean>()
    //수정이 안되는 sharedFlow
    val undoSharedFlow = _undoSharedFlow.asSharedFlow()

    private val _redoSharedFlow = MutableSharedFlow<Boolean>()
    //수정이 안되는 sharedFlow
    val redoSharedFlow = _redoSharedFlow.asSharedFlow()

    //뒤로 가기
    fun undo(){
        viewModelScope.launch{
            _undoSharedFlow.emit(true)
        }
    }

    //앞으로 가기
    fun redo(){
        viewModelScope.launch{
            _redoSharedFlow.emit(true)
        }
    }
}