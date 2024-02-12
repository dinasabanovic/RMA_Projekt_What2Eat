package com.example.what2eat.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.what2eat.data.dto.recipe_details.RecipeDetailsDto

@Database(
    entities = [RecipeDetailsDto::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(IngredientsConverter::class, DishTypesConverter::class)
abstract class RecipesDatabase : RoomDatabase() {
    abstract fun recipeDetailsDao(): RecipeDetailsDao
}