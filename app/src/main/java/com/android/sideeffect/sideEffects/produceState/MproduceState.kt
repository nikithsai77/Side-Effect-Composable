package com.android.sideeffect.sideEffects.produceState

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun MProduceState(produceViewModel: ProduceViewModel) {
    val ownerLifecycle = LocalLifecycleOwner.current
    val state by produceState(initialValue = "", key1 = produceViewModel) {
        produceViewModel.getFlow().flowWithLifecycle(lifecycle = ownerLifecycle.lifecycle).collect {
            value = "$value\n$it"
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = state)
    }
}

class ProduceViewModel : ViewModel() {

    fun getFlow() : Flow<String> = flow {
        emit(value = "First Value")
        delay(timeMillis = 5000)
        emit(value = "Second Value")
    }

    suspend fun getSuspend() : String {
        delay(timeMillis = 500)
        return "This is the suspend function"
    }

    fun getUserInfo(data: (String) -> Unit) {
        data("This is the call back function")
    }

}
