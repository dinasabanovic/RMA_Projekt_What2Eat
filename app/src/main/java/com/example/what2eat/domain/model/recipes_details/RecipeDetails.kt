package com.example.what2eat.domain.model.recipes_details

data class RecipeDetails(
    val id: Int,
    val title: String,
    val image: String,
    val aggregateLikes: Int,
    val ingredients: List<Ingredient>,
    val dishTypes: List<String>,
    val instructions: String?,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
)