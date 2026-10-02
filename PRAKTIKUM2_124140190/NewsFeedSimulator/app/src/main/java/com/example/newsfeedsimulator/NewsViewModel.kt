package com.example.newsfeedsimulator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewsViewModel : ViewModel() {

    private val _readCount = MutableStateFlow(0)

    val readCount: StateFlow<Int> = _readCount.asStateFlow()

    fun markAsRead() {
        _readCount.value++
    }
}