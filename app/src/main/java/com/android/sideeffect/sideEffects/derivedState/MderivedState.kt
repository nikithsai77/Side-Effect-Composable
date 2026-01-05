package com.android.sideeffect.sideEffects.derivedState

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DerivedState() {
    var list by remember {
        mutableStateOf(value = todos)
    }
    val selectedList by remember {
        derivedStateOf {
            list.filter {
                it.isSelected
            }
        }
    }
    val unSelectedList by remember {
        derivedStateOf {
            list.filter {
                !it.isSelected
            }
        }
    }
    LazyColumn {
        item {
            Text(text = "Selected Item's are ")
            Spacer(modifier = Modifier.height(height = 20.dp))
        }

        items(
            items = selectedList,
            key = { it.id }
        ) {
            RecomposeLogger(tag = it.name)
            Text(
                text = "${it.createdAt} - ${it.name}",
                modifier = Modifier.clickable {
                    val newList = list.toMutableList()
                    newList.add(
                        UserInfo(
                            id = list.size + 1,
                            name = "okay ${list.size + 1}",
                            isSelected = false
                        )
                    )
                    list = newList.toList()
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(height = 20.dp))
            Text(text = "Un Selected Item's are ")
            Spacer(modifier = Modifier.height(height = 20.dp))
        }

        items(
            items = unSelectedList,
            key = { it.id }
        ) {
            RecomposeLogger(tag = it.name)
            Text(
                text = "${it.createdAt} - ${it.name}",
                modifier = Modifier.clickable {
                    val newList = list.toMutableList()
                    newList.add(
                        UserInfo(
                            id = list.size + 1,
                            name = "okay ${list.size + 1}",
                            isSelected = true
                        )
                    )
                    list = newList.toList()
                }
            )
        }
    }
}

@Composable
fun RecomposeLogger(tag: String) {
    SideEffect {
        println("kk aa aa $tag")
    }
}

data class UserInfo(
    val id: Int,
    val name: String = "",
    val isSelected: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

var todos = listOf(
    UserInfo(
        id = 1,
        name = "okay 1",
        isSelected = false
    ),
    UserInfo(
        id = 2,
        name = "okay 2",
        isSelected = true
    ),
    UserInfo(
        id = 3,
        name = "okay 3",
        isSelected = false
    ),
    UserInfo(
        id = 4,
        name = "okay 4",
        isSelected = true
    ),
    UserInfo(
        id = 5,
        name = "okay 5",
        isSelected = false
    ),
    UserInfo(
        id = 6,
        name = "okay 6",
        isSelected = true
    ),
    UserInfo(
        id = 7,
        name = "okay 7",
        isSelected = false
    ),
    UserInfo(
        id = 8,
        name = "okay 8",
        isSelected = true
    ),
    UserInfo(
        id = 9,
        name = "okay 9",
        isSelected = false
    ),
    UserInfo(
        id = 10,
        name = "okay 10",
        isSelected = true
    )
)
