package com.example.what2eat.screens.recipes_details_screen

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.what2eat.domain.model.recipes_details.RecipeDetails
import com.example.what2eat.screens.recipes_details_screen.components.DetailsHeader
import com.example.what2eat.screens.recipes_details_screen.components.DetailsItems
import com.example.what2eat.screens.recipes_details_screen.components.DishTypes
import com.example.what2eat.screens.recipes_details_screen.components.Ingredients
import com.example.what2eat.screens.recipes_details_screen.components.Navbar
import com.example.what2eat.utils.ErrorView
import com.example.what2eat.utils.HttpStatusCode
import com.example.what2eat.utils.LoadingView

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun RecipesDetailsScreen(
    navController: NavController,
    recipeId: Int,
    viewModel: RecipesDetailsScreenViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = recipeId) {
        viewModel.getRecipeDetails(recipeId) // Fetch recipe details based on the ID
    }

    when (val recipeDetailsState = viewModel.uiState.collectAsStateWithLifecycle().value) {
        is RecipeDetailsUiState.Loading -> LoadingView()
        is RecipeDetailsUiState.Error -> ErrorView(message = HttpStatusCode.getMeaningfulMessage(
            recipeDetailsState.message, LocalContext.current
        ), onClickRetry = { })
        is RecipeDetailsUiState.Loaded -> RecipeDetails(
            recipeDetails = recipeDetailsState.data, navController = navController
        )
    }
}

@Composable
fun RecipeDetails(navController: NavController, recipeDetails: RecipeDetails) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        Navbar(navController, recipeDetails)
        Column(
            modifier = Modifier
                .scrollable(rememberScrollState(), Orientation.Horizontal)
                .weight(weight = 1f, fill = false)
                .fillMaxSize()
        ) {
            DetailsHeader(recipeDetails)
            DetailsItems(recipeDetails)
            DishTypes(recipeDetails.dishTypes)
            Ingredients(recipeDetails.ingredients)
        }
    }
}