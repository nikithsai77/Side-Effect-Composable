package com.android.sideeffect.sideEffects.produceState

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import kotlinx.coroutines.delay

//produceState is used to convert non-compose state to compose state.
//it has a parameter called produceState launches a coroutine it set the value of state and return it.
//producer launched when produceState enters the composition and will be cancelled when the producer state leaves the composition
//as well as it has a awaitDispose here we can clean up resource's.
//best use cases are LiveData, Flow, API, RXJava to use it here.
@Composable
fun M_ProduceState() {
    val state by produceState(initialValue = 0) {
        delay(timeMillis = 1000)
        value = 10
        delay(timeMillis = 1000)
        value = 20
    }
    Button(onClick = {}) {
        Text(text = "$state")
    }
}