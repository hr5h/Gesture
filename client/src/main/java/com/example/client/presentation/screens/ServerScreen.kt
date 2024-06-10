package com.example.client.presentation.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.client.ClientState
import com.example.client.data.NameEntity
import com.example.client.ui.theme.Green
import com.example.client.ui.theme.Lavender
import com.example.client.ui.theme.PastelBlue
import com.example.client.ui.theme.Red
import kotlin.math.abs

@Composable
fun ServerScreen(
    changePage: (String) -> Unit,
    addSwipe: (NameEntity) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(PastelBlue)
        ) {
            IconButton(onClick = {
                changePage("main")
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    tint = Color.White
                )
            }
        }
        GetGesture(addSwipe)
    }
}

@Composable
fun GetGesture(
    addSwipe: (NameEntity) -> Unit
) {
    val showToast = remember {
        mutableStateOf(false)
    }

    val swipe = remember {
        mutableStateOf("")
    }

    val colorB = remember {
        mutableStateOf(Lavender)
    }

    if (!showToast.value) {
        showToast.value = true
        val r = (1..4).random()
        when (r) {
            1 -> swipe.value = "right"
            2 -> swipe.value = "left"
            3 -> swipe.value = "up"
            4 -> swipe.value = "down"
        }
        Log.d("MyLog", swipe.value)
    }

    val offsetX = remember { mutableFloatStateOf(0f) }
    val offsetY = remember { mutableFloatStateOf(0f) }
    var tempPath = Path()
    val path = remember {
        mutableStateOf(Path())
    }
    var valueX = 0f
    var valueY = 0f
    var x1 = 0f
    var y1 = 0f
    var x2 = 0f
    var y2 = 0f
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorB.value),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.size(15.dp))
        Text(
            text = "Swipe: ${swipe.value}",
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(Lavender)
                .padding(10.dp),
            fontSize = 30.sp,
        )
        Canvas(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .clip(RoundedCornerShape(35.dp))
            .background(Color.White)
            .pointerInput(Unit) { ->
                detectDragGestures(
                    onDragStart = {
                        Log.d(
                            "MyLog",
                            "start: x = ${offsetX.floatValue}, y = ${offsetY.floatValue}"
                        )
                        valueX = offsetX.floatValue
                        valueY = offsetY.floatValue
                        x1 = offsetX.floatValue
                        y1 = offsetY.floatValue
                    },
                    onDragEnd = {
                        Log.d(
                            "MyLog",
                            "end: x = ${offsetX.floatValue}, y = ${offsetY.floatValue}"
                        )
                        valueX = offsetX.floatValue - valueX
                        valueY = offsetY.floatValue - valueY
                        x2 = offsetX.floatValue
                        y2 = offsetY.floatValue
                        var result = ""
                        if (abs(valueX) > 150) {
                            if (x2 > x1) {
                                if (swipe.value == "right") colorB.value = Green
                                else colorB.value = Red
                                result = "Right"
                                showToast.value = false
                            } else {
                                if (swipe.value == "left") colorB.value = Green
                                else colorB.value = Red
                                result = "Left"
                                showToast.value = false
                            }
                        } else if (abs(valueY) > 150) {
                            if (y2 > y1) {
                                if (swipe.value == "down") colorB.value = Green
                                else colorB.value = Red
                                result = "Down"
                                showToast.value = false
                            } else {
                                if (swipe.value == "up") colorB.value = Green
                                else colorB.value = Red
                                result = "Up"
                                showToast.value = false
                            }
                        }
                        addSwipe(
                            NameEntity(
                                task = swipe.value,
                                swipe = result,
                                result = colorB.value == Green
                            )
                        )
                        tempPath = Path()
                        path.value = Path()
                    }
                )
                { change, dragAmount ->
                    tempPath.moveTo(
                        change.position.x - dragAmount.x,
                        change.position.y - dragAmount.y
                    )
                    tempPath.lineTo(
                        change.position.x,
                        change.position.y
                    )
                    path.value = Path().apply {
                        addPath(tempPath)
                    }
                    offsetX.floatValue += dragAmount.x
                    offsetY.floatValue += dragAmount.y
                }
            }) {
            drawPath(
                path.value,
                color = PastelBlue,
                style = Stroke(15f)
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewServerScreen() {
    ServerScreen({}, {})
}