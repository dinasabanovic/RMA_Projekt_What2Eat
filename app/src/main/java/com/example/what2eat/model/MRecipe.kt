package com.example.what2eat.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.PropertyName

data class MRecipe(
    @Exclude var id: String? = null,
    var title: String? = null,
    var image: String? = null,
    @get:PropertyName("user_id")
    @set:PropertyName("user_id")
    var userId: String? = null,
    @get:PropertyName("api_recipe_id")
    @set:PropertyName("api_recipe_id")
    var apiRecipeId: Int? = null
)
