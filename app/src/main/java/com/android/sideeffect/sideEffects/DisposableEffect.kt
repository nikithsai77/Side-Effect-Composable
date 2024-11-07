package com.android.sideeffect.sideEffects

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember

@Composable
fun M_disposableEffect(backPressedDispatcher: OnBackPressedDispatcher) {
    val callback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                //do something.
            }
        }
    }
    //disposable effect is a life cycle aware is used to do clean up resources like
    //sometimes we are adding callbacks then if composable leaves then we need to remove
    //that callback so disposable has a method called onDispose, this will be called
    //when composable leaves or died.
    DisposableEffect(key1 = true) {
        backPressedDispatcher.addCallback(callback)

        onDispose {
            //do not pass state or context to key1 bez if it updated then onDispose think composable destroyed so this onDispose will execute.
            callback.remove()
        }
    }
}