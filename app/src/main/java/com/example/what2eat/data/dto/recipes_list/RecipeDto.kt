package com.example.what2eat.data.dto.recipes_list

import com.example.what2eat.domain.model.recipes_list.Recipe

data class RecipeDto(
    val id: Int,
    val title: String,
    val image: String
)

fun RecipeDto.toRecipe(): Recipe {
    return Recipe(
        id = id,
        title = title,
        image = image,
    )
}