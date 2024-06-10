package com.example.client.presentation.screens

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.client.data.NameEntity
import com.example.client.ui.theme.Lavender
import com.example.client.ui.theme.PastelBlue

@Composable
fun LogScreen(
    itemsList: List<NameEntity>,
    changePage: (String) -> Unit,
    deleteItem: (NameEntity) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Lavender)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(PastelBlue),
            horizontalArrangement = Arrangement.SpaceBetween
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
            IconButton(onClick = {
                for (it in itemsList) {
                    deleteItem(it)
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete",
                    tint = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(itemsList) { item ->
                ListItem(item)
            }
        }
    }
    Column {
        Spacer(modifier = Modifier.height(45.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(PastelBlue)
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Task",
                fontSize = 22.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp, top = 5.dp, bottom = 5.dp, end = 70.dp)
            )
            Text(
                text = "Swipe",
                fontSize = 22.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 40.dp, top = 5.dp, bottom = 5.dp, end = 40.dp)
            )
            Text(
                text = "Result",
                fontSize = 22.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 30.dp, top = 5.dp, bottom = 5.dp, end = 10.dp)
            )
        }
    }
}

@Composable
fun ListItem(entity: NameEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(35.dp))
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(PastelBlue),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = entity.task.replaceFirst(
                    entity.task.first(),
                    entity.task.first().uppercaseChar()
                ),
                fontSize = 22.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp, top = 5.dp, bottom = 5.dp).fillMaxWidth(0.3f)
            )
            Text(
                text = entity.swipe,
                fontSize = 22.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp).fillMaxWidth(0.3f)
            )
            Text(
                text = entity.result.toString().replaceFirst(
                    entity.result.toString().first(),
                    entity.result.toString().first().uppercaseChar()
                ),
                fontSize = 22.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 5.dp, top = 5.dp, bottom = 5.dp, end = 10.dp).fillMaxWidth(0.35f)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewLogScreen() {
    LogScreen(
        listOf(
            NameEntity(null, "Right", "Left", true),
            NameEntity(null, "Right", "Left", true),
            NameEntity(null, "Right", "Left", true)
        ), {}, {})
}

@Composable
@Preview(showBackground = true)
fun PreviewListItem() {
    ListItem(NameEntity(null, "Right", "Left", true))
}