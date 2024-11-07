package com.android.sideeffect.sideEffects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect

var i = 0

@Composable
fun M_sideEffect() {
    //side effect is the non life cycle aware has a block of code this will execute after every successful recomposition
    //It is useful for synchronizing with non compose code.
    //this completely depends upon what happening on composable not depends on composable either opened or closed.
    SideEffect {
        i++
    }
}