package com.android.sideeffect.sideEffects

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.sideeffect.ui.theme.SideEffectTheme
import kotlinx.coroutines.launch

val list = IntArray(1000) { it }.toList()

//derivedStateOf contains block of code calculation, that cal result will update the
//state value. so here we can pass another state obj so based upon we can update our state.
//below block of code contains the scrollState that block will executes whenever changes occur on scrollState
@Composable
fun M_derivedState() {
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val isButton by remember {
        derivedStateOf {
            scrollState.firstVisibleItemIndex > 8
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        LazyColumn(state = scrollState) {
            items(list) {
                Text(text = "Value is $it", fontSize = 20.sp, modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp))
            }
        }
    }
    AnimatedVisibility(visible = isButton) {
        FloatingActionButton(
            modifier = Modifier.height(100.dp).width(100.dp),
            onClick = { scope.launch {
            scrollState.animateScrollToItem(index = 0)
        } }) {
            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
        }
    }
}

@Preview
@Composable
fun DerivedStatePreview() {
    SideEffectTheme {
        M_derivedState()
    }
}