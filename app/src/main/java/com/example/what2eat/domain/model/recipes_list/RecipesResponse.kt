package com.example.what2eat.domain.model.recipes_list

data class RecipesResponse(
    val offset: Int,
    val results: List<Recipe>,
)