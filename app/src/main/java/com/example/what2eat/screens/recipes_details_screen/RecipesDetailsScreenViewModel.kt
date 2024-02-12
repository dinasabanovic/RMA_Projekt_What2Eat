package com.example.what2eat.screens.recipes_details_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.what2eat.data.remote.Constants
import com.example.what2eat.domain.usecase.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesDetailsScreenViewModel @Inject constructor(
    private val recipesUseCase: GetRecipesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<RecipeDetailsUiState>(RecipeDetailsUiState.Loading)
    val uiState: StateFlow<RecipeDetailsUiState> = _uiState

    init {
        savedStateHandle.get<Int>(Constants.PARAM_ID)?.let { id ->
            getRecipeDetails(id)
        }
    }

    fun getRecipeDetails(id: Int) {
        _uiState.value = RecipeDetailsUiState.Loading
        viewModelScope.launch {
            try {
                _uiState.value = RecipeDetailsUiState.Loaded(recipesUseCase.getRecipeDetails(id))
            } catch (exception: Exception) {
                _uiState.value = RecipeDetailsUiState.Error(exception.message.toString())
            }
        }
    }
}