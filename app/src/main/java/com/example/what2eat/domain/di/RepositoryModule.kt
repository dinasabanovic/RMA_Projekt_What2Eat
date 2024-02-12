package com.example.what2eat.domain.di

import com.example.what2eat.data.dto.repository.RecipeRepositoryImpl
import com.example.what2eat.data.local.RecipeDetailsDao
import com.example.what2eat.data.remote.FoodRecipesApi
import com.example.what2eat.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRecipeRepository(
        api: FoodRecipesApi, recipeDetailsDao: RecipeDetailsDao
    ): RecipeRepository {
        return RecipeRepositoryImpl(api, recipeDetailsDao)
    }
}