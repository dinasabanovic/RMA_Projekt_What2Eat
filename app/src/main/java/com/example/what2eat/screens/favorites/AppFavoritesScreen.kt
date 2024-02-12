package com.example.what2eat.screens.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.what2eat.R
import com.example.what2eat.components.BottomNavigationBar
import com.example.what2eat.navigation.AppScreens
import com.example.what2eat.screens.favorites.components.MRecipeItem
import com.example.what2eat.screens.theme.TextPrimary
import com.example.what2eat.screens.theme.TextSecondary
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    navController: NavController,
    viewModel: FavoritesScreenViewModel = hiltViewModel()
) {
    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName = if (!email.isNullOrEmpty())
        FirebaseAuth.getInstance().currentUser?.email?.split("@")?.get(0) else "N/A"

    val currentUser = FirebaseAuth.getInstance().currentUser
    val currentUserId = currentUser?.uid

    val allRecipes = viewModel.data.value.data ?: emptyList()
    val userRecipes = allRecipes.filter { it.userId == currentUserId }

    val loading = viewModel.data.value.loading

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier.padding(bottom = 20.dp, top = 20.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Sign out",
                        modifier = Modifier.clickable {
                            FirebaseAuth.getInstance().signOut().run {
                                navController.navigate(AppScreens.LoginScreen.name)
                            }
                        }
                    )
                    Row() {
                        Column {
                            Text(
                                text = stringResource(R.string.recipes_welcome_message),
                                color = TextSecondary,
                                fontSize = 16.sp,
                            )
                            Text(
                                text = currentUserName!!,
                                color = TextPrimary,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(top = 6.dp),
                            )
                        }
                        Image(
                            painterResource(R.drawable.profile_image),
                            contentDescription = "profile_image",
                            modifier = Modifier
                                .size(54.dp)
                                .clip(RoundedCornerShape(80.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            if (userRecipes.isEmpty()) {
                // Show "No saved recipes yet" message when there are no recipes
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No saved recipes yet.",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                // Show the list of user recipes or loading indicator
                if (loading == true) {
                    // Show circular progress indicator while loading
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(48.dp) // Set your desired size for the indicator
                            .padding(vertical = 16.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                } else {
                    // Show the list of user recipes
                    LazyColumn {
                        items(userRecipes.size) { index ->
                            MRecipeItem(userRecipes[index], navController)
                        }
                    }
                }
            }
        }
    }
}