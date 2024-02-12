package com.example.what2eat.screens.recipes_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.what2eat.domain.model.recipes_list.Recipe
import com.example.what2eat.navigation.AppScreens
import com.example.what2eat.screens.Screen
import com.example.what2eat.screens.theme.ImageHover

@Composable
fun RecipeItem(
    recipe: Recipe, navController: NavController
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 20.dp)
        .height(220.dp)
        .clickable {
            navController.navigate("${AppScreens.RecipesDetailsScreen.name}/${recipe.id}")
        }) {
        Image(
            painter = rememberAsyncImagePainter(recipe.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.FillBounds,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
                .background(ImageHover)
        )
        Text(
            text = recipe.title,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(all = 16.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )
    }
}
