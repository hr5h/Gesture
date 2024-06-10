package com.example.server

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import io.ktor.server.engine.embeddedServer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import java.time.*
import java.util.*
import java.util.concurrent.*


class MainViewModel : ViewModel() {
    val state = MutableStateFlow(ServerState())

    fun savePort(port: String) {
        state.update { it.copy(port = port) }
    }

    fun startServer() {

    }
}