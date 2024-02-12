package com.example.what2eat.domain.repository

import com.example.what2eat.data.dto.recipe_details.RecipeDetailsDto
import com.example.what2eat.domain.model.recipes_list.RecipesResponse

interface RecipeRepository {
    suspend fun fetchRecipes(offset: Int): RecipesResponse

    suspend fun fetchRecipesDetails(id: Int): RecipeDetailsDto

    suspend fun getRecipeDetails(id: Int): RecipeDetailsDto?

    suspend fun cacheRecipeDetails(recipeDetails: RecipeDetailsDto)
}