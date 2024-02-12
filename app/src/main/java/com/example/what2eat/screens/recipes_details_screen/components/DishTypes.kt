package com.example.what2eat.screens.recipes_details_screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishTypes(dishTypes: List<String>) {
    LazyRow(modifier = Modifier.padding(top = 10.dp)) {
        items(dishTypes.size) { index ->
            AssistChip(
                label = {Text(dishTypes[index])},
                modifier = Modifier.padding(end = 6.dp),
                onClick = { },
            )
        }
    }
}