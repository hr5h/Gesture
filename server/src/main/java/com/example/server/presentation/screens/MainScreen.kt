package com.example.server.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.server.ServerState
import com.example.server.ui.theme.Lavender
import com.example.server.ui.theme.PastelBlue

@Composable
fun MainScreen(
    state: ServerState,
    savePort: (String) -> Unit,
    startServer: () -> Unit
) {
    val showDialog = remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Lavender
    ) {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Port: ${state.port}",
                    fontSize = 30.sp
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PastelBlue),
                    onClick = {
                        showDialog.value = true
                    }) {
                    Text(text = "Config", fontSize = 30.sp)
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PastelBlue),
                    onClick = {
                        startServer()
                    }) {
                    Text(text = "Начать", fontSize = 30.sp)
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PastelBlue),
                    onClick = {

                    }) {
                    Text(text = "Логи", fontSize = 30.sp)
                }
            }
            if (showDialog.value) {
                MyDialog(
                    state,
                    showDialog,
                    savePort
                )
            }
        }
    }
}

@Composable
fun MyDialog(
    state: ServerState,
    showDialog: MutableState<Boolean>,
    savePort: (String) -> Unit
) {
    val textPortState = remember {
        mutableStateOf(state.port)
    }
    Dialog(onDismissRequest = { showDialog.value = false }) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = textPortState.value,
                onValueChange = { textPortState.value = it },
                label = {
                    Text(text = "Введите Порт")
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White
                ),
                textStyle = TextStyle(Color.Black, fontSize = 14.sp)
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(start = 50.dp, end = 50.dp, top = 10.dp, bottom = 10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PastelBlue),
                onClick = {
                    savePort(textPortState.value)
                    showDialog.value = false
                }
            ) {
                Text(text = "Сохранить")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(
        ServerState(
            port = "448"
        ), {}, {})
}


@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewDialog() {
    MyDialog(ServerState(
        port = "448"
    ),
        mutableStateOf(true), {})
}