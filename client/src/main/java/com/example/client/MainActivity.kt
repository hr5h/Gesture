package com.example.client

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.client.presentation.screens.LogScreen
import com.example.client.presentation.screens.MainScreen
import com.example.client.presentation.screens.ServerScreen
import com.example.client.ui.theme.GestureTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by lazy {
        MainViewModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.state.collectAsState()
            val itemsList = viewModel.itemsList.collectAsState(initial = emptyList())
            for (it in itemsList.value) {
                println(it)
            }
            GestureTheme {
                if (state.page == "server") {
                    ServerScreen(
                        changePage = viewModel::changePage,
                        addSwipe = viewModel::insertItem
                    )
                } else if (state.page == "logs") {
                    LogScreen(
                        itemsList = itemsList.value,
                        changePage = viewModel::changePage,
                        deleteItem = viewModel::deleteItem
                    )
                } else {
                    MainScreen(
                        state = state,
                        saveIpPort = viewModel::saveIpPort,
                        changePage = viewModel::changePage
                    )
                }
            }
        }
    }
}
