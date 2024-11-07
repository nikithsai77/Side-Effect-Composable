Writing non-composable code in the composable function or block is not a good way to implement it for example below.

Example:: 

var i = 0

@Composable
fun NonComposable() {
 i++
 Text(text = "Sample")
}

 In the above code, we are incrementing i++ to do something with it on UI Composable, so writing like this is not good practice. Here, we should use State Management & Transformation, Life Cycle Aware, and Non-Life Cycle Aware.

State Management and Transformation:: It ensures that the UI updates correctly when data changes those are:: remember & mutableStateOf(), derivedStateOf(), produceState, rememberUpdateState.
Life Cycle Aware:: It is life cycle aware of Composable's Those are rememberCoroutineScope, LaunchedEffect, DisposableEffect. 
Non-Life Cycle Aware:: It is Non-Life Cycle Aware of Composable are SideEffect.
 
