package com.example.what2eat.screens.recipes_details_screen.components

import android.util.Log
import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.what2eat.screens.theme.TextPrimary
import com.example.what2eat.R
import com.example.what2eat.domain.model.recipes_details.RecipeDetails
import com.example.what2eat.model.MRecipe
import com.example.what2eat.navigation.AppScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun Navbar(navController: NavController, recipeDetails: RecipeDetails) {
    val context = LocalContext.current

    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painterResource(R.drawable.ic_back_arrow),
            contentDescription = "back_button",
            modifier = Modifier
                .size(24.dp)
                .clickable { navController.navigateUp() },
        )
        Text(
            text = stringResource(R.string.recipe_details_title),
            color = TextPrimary,
            fontSize = 18.sp,
        )
        Image(
            painterResource(R.drawable.heart_full),
            contentDescription = "menu_button",
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    val recipe = MRecipe(
                        title = recipeDetails.title,
                        image = recipeDetails.image,
                        apiRecipeId = recipeDetails.id,
                        userId = FirebaseAuth.getInstance().currentUser?.uid.toString()
                    )
                    Toast.makeText(context, "Recipe added to favorites!", Toast.LENGTH_SHORT).show()
                    SaveToFirebase(recipe, navController)
                }
        )
    }
}

fun SaveToFirebase(recipe: MRecipe, navController: NavController) {
    val db = FirebaseFirestore.getInstance()
    val dbCollection = db.collection("recipes")


    if(recipe.toString().isNotEmpty()) {
        dbCollection.add(recipe)
            .addOnSuccessListener { documentRef ->
                val docId = documentRef.id
                dbCollection.document(docId)
                    .update(hashMapOf("id" to docId) as Map<String, Any>)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            navController.navigate(AppScreens.FavoritesScreen.name)
                        }
                    }
            }
    }else {
        Log.d("ADD RECIPE", "Something went wrong.")
    }
}

