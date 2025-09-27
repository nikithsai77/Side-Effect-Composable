Writing non-composable code in the composable function or block is not a good way to implement it
for example below.

Example::

var i = 0

@Composable
fun NonComposable() {
i++
Text(text = "Sample")
}

In the above code, we are incrementing i++ to do something with it on UI Composable, so writing like
this is not good practice. Here, we should use State Management & Transformation, Life Cycle Aware,
and Non-Life Cycle Aware.

State Management and Transformation:: It ensures that the UI updates correctly when data changes
those are:: remember & mutableStateOf(), derivedStateOf(), produceState, rememberUpdateState.
Life Cycle Aware:: It is life cycle aware of Composable's Those are rememberCoroutineScope,
LaunchedEffect, DisposableEffect.
Non-Life Cycle Aware:: It is Non-Life Cycle Aware of Composable are SideEffect.


2) Produce State : is the composable function it is used to convert the non state/non composable to
   compose state like flow, suspend functions, callbacks.

Example ::

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
