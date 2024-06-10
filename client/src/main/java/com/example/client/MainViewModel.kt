package com.example.client

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.PendingIntentCompat.send
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.client.data.MainDb
import com.example.client.data.NameEntity
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.websocket.Frame
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(context: Context): ViewModel() {
    val state = MutableStateFlow(ClientState())
    private val database = MainDb.createDataBase(context)
    val itemsList = database.dao.getAllItems()

    fun saveIpPort(ip: String, port: String){
        state.update { it.copy(ip = ip, port = port) }
    }

    fun changePage(page: String) {
        state.update { it.copy(page = page) }
    }

    fun insertItem(entity: NameEntity) = viewModelScope.launch {
        database.dao.insertItem(entity)
    }

    fun deleteItem(entity: NameEntity) = viewModelScope.launch {
        database.dao.deleteItem(entity)
    }
}