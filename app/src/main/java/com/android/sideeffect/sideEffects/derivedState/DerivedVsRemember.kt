@file:OptIn(ExperimentalMaterial3Api::class)

package com.android.sideeffect.sideEffects.derivedState

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun DerivedVsRemember() {
    val state = rememberLazyListState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = { ScrollToTopButton(state) }
    ) { paddingValues ->
        LazyColumn(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(all = 16.dp)
        ) {
            items(count = 100) {
                Text(
                    text = "Item $it",
                    modifier = Modifier.padding(all = 16.dp)
                )
            }
        }
    }
}

@Composable
fun ScrollToTopButton(
    state: LazyListState
) {
    SideEffect {
        println("kk This is Used to know the how many times recomposition is occurring by using of derivedStateOf (or) remember(state.firstVisibleItemIndex) ")
    }

    val scope = rememberCoroutineScope()

    val showScrollTopPosition by remember {
        derivedStateOf {
            state.firstVisibleItemIndex >= 5
        }
    }

//    val showScrollTopPosition = remember(state.firstVisibleItemIndex) {
//        state.firstVisibleItemIndex >= 5
//    }

    if (showScrollTopPosition) {
        FloatingActionButton(onClick = {
            scope.launch {
                state.scrollToItem(index = 0)
            }
        }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = null
            )
        }
    }
}
