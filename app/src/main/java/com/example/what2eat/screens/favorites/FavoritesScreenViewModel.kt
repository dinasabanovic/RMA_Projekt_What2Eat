package com.example.what2eat.screens.favorites

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.what2eat.data.DataOrException
import com.example.what2eat.domain.repository.FireRepository
import com.example.what2eat.model.MRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesScreenViewModel @Inject constructor(
    private val repository: FireRepository
): ViewModel() {
    val data: MutableState<DataOrException<List<MRecipe>, Boolean, Exception>>
            = mutableStateOf(DataOrException(listOf(), true,Exception("")))

    init {
        getAllRecipesFromdatabase()
    }

    private fun getAllRecipesFromdatabase() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getAllRecipesFromDatabase()
            if (!data.value.data.isNullOrEmpty()) data.value.loading = false
        }
        Log.d("GET", "getAllRecipesFromDatabase: ${data.value.data?.toList().toString()}")

    }
}