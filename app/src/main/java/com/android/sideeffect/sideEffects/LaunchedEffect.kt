package com.android.sideeffect.sideEffects

import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

//LaunchedEffect is a Life-cycle aware and it is used to start the coroutine implicitly withOut Explicitly.
//The best use case is when u want to hit the back-end server when the app launches
//this launches the coroutine when composable enter and destroy itself when composable destroyed.
@Composable
fun M_LaunchedEffect() {
    val context = LocalContext.current
    var counter by remember {
        mutableStateOf(value = 0)
    }
    LaunchedEffect(key1 = counter) {
        incrementing()
        Toast.makeText(context, "Launched Effect",Toast.LENGTH_LONG).show()
    }
    Button(onClick = { counter++ }) {
        Text(text = "onClick")
    }
}

suspend fun incrementing() {
    for (i in 0..100) {
        delay(timeMillis = 1000)
        println("kk cc: $i")
    }
}

@Preview
@Composable
fun Preview() {
    M_LaunchedEffect()
}