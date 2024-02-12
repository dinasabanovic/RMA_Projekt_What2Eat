package com.example.what2eat.data.remote

import com.example.what2eat.data.dto.recipe_details.RecipeDetailsDto
import com.example.what2eat.data.dto.recipes_list.RecipesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodRecipesApi {
    @GET("complexSearch")
    suspend fun getRandomRecipes(@Query("offset") pageNumber: Int): RecipesResponseDto

    @GET("/recipes/{id}/information")
    suspend fun getRecipeDetails(@Path("id") id: Int): RecipeDetailsDto

}