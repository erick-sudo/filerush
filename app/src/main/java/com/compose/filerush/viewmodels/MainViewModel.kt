package com.compose.filerush.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {
    private val _drawerOpened = MutableStateFlow(false)
    val drawerOpened = _drawerOpened.asStateFlow()

    fun openDrawer() {
        _drawerOpened.value = true
    }

    fun resetDrawer() {
        _drawerOpened.value = false
    }
}