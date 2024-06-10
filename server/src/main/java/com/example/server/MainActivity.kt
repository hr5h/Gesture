package com.example.server

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.server.presentation.screens.MainScreen
import com.example.server.ui.theme.GestureTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by lazy {
        MainViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.state.collectAsState()
            GestureTheme {
                MainScreen(
                    state = state,
                    savePort = viewModel::savePort,
                    startServer = viewModel::startServer
                )
            }
        }
    }
}