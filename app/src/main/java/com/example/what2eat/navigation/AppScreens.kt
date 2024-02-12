package com.example.what2eat.navigation

import java.lang.IllegalArgumentException

enum class AppScreens {
    SplashScreen,
    LoginScreen,
    CreateAccountScreen,
    RecipesScreen,
    RecipesDetailsScreen,
    FavoritesScreen;

    companion object {
        fun fromRoute(route: String): AppScreens = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            CreateAccountScreen.name -> CreateAccountScreen
            RecipesDetailsScreen.name -> RecipesDetailsScreen
            RecipesScreen.name -> RecipesScreen
            FavoritesScreen.name -> FavoritesScreen
            null -> RecipesScreen
            else -> throw IllegalArgumentException("Route ${route} is not recognised.")
        }
    }
}