package com.example.what2eat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.what2eat.screens.login.LoginScreen
import com.example.what2eat.screens.recipes_details_screen.RecipesDetailsScreen
import com.example.what2eat.screens.AppSplashScreen
import com.example.what2eat.screens.favorites.FavoritesScreen
import com.example.what2eat.screens.recipes_screen.RecipesScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.name) {
        composable(AppScreens.SplashScreen.name) {
            AppSplashScreen(navController = navController)
        }
        composable(AppScreens.RecipesScreen.name) {
            RecipesScreen(navController = navController)
        }
        composable(
            "${AppScreens.RecipesDetailsScreen.name}/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
            // ^ Define route with a parameter for recipeId of type Int
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: 0 // Default value or handle null case
            RecipesDetailsScreen(navController = navController, recipeId = recipeId)
        }
        composable(AppScreens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }
        composable(AppScreens.FavoritesScreen.name) {
            FavoritesScreen(navController = navController)
        }
    }
}