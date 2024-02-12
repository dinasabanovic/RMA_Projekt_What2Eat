package com.example.what2eat.screens.recipes_details_screen

import com.example.what2eat.domain.model.recipes_details.RecipeDetails

sealed class RecipeDetailsUiState {
    object Loading : RecipeDetailsUiState()
    class Loaded(val data: RecipeDetails) : RecipeDetailsUiState()
    class Error(val message: String) : RecipeDetailsUiState()
}