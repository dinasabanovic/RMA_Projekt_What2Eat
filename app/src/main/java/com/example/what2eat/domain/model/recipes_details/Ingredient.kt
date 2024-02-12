package com.example.what2eat.domain.model.recipes_details

data class Ingredient(
    val image: String?,
    val name: String,
    val amount: Double,
    val unit: String,
)