package com.example.what2eat.screens.favorites.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.what2eat.model.MRecipe
import com.example.what2eat.navigation.AppScreens
import com.example.what2eat.screens.theme.ImageHover
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun MRecipeItem(
    recipe: MRecipe,
    navController: NavController
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .height(220.dp)
            .clickable {
                // Handle the click on the entire recipe item (excluding the delete button)
                navController.navigate("${AppScreens.RecipesDetailsScreen.name}/${recipe.apiRecipeId}")
            }
    ) {
        // Image and other content for displaying the recipe item
        Image(
            painter = rememberAsyncImagePainter(recipe.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.FillBounds,
        )

        // Display your existing content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Black.copy(alpha = 0.5f))
        )
        Text(
            text = recipe.title ?: "Recipe Title", // Update with your handling of null title
            modifier = Modifier
                .align(Alignment.Center)
                .padding(all = 16.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )

        Button(
            onClick = {
                // Handle the delete button click
                FirebaseFirestore.getInstance()
                    .collection("recipes")
                    .document(recipe.id!!)
                    .delete()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            navController.navigate(AppScreens.FavoritesScreen.name)
                            Toast.makeText(context, "Recipe Deleted", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Failed to delete recipe", Toast.LENGTH_SHORT).show()
                        }
                    }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text(text = "Delete")
        }
    }
}