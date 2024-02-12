package com.example.what2eat.data.dto.repository

import com.example.what2eat.data.dto.recipe_details.RecipeDetailsDto
import com.example.what2eat.data.dto.recipes_list.toRecipesResponse
import com.example.what2eat.data.local.RecipeDetailsDao
import com.example.what2eat.data.remote.FoodRecipesApi
import com.example.what2eat.domain.model.recipes_list.RecipesResponse
import com.example.what2eat.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val api: FoodRecipesApi,
    private val recipeDetailsDao: RecipeDetailsDao
) : RecipeRepository {

    override suspend fun fetchRecipes(offset: Int): RecipesResponse {
        return api.getRandomRecipes(offset).toRecipesResponse()
    }

    override suspend fun fetchRecipesDetails(id: Int): RecipeDetailsDto {
        return api.getRecipeDetails(id)
    }

    override suspend fun getRecipeDetails(id: Int): RecipeDetailsDto? {
        return recipeDetailsDao.getRecipeDetails(id)
    }

    override suspend fun cacheRecipeDetails(recipeDetails: RecipeDetailsDto) {
        recipeDetailsDao.cacheRecipeDetails(recipeDetails)
    }
}