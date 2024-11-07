package com.android.sideeffect.sideEffects

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//rememberCoroutineScope is a life-cycle aware if composable exit then all
//all coroutine's which are launched in this scope will get destroy.
@Composable
fun M_RememberCoroutineScope() {
    val scope = rememberCoroutineScope()
    Button(onClick = { scope.launch {  increment() } }) {
        Text(text = "Click Here To Start The Coroutine")
    }
}

suspend fun increment() {
    for (i in 0..100) {
        delay(timeMillis = 1000)
        println("kk cc: $i")
    }
}

@Preview
@Composable
fun MPreview() {
    M_RememberCoroutineScope()
}