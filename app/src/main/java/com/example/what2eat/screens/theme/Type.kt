package com.example.what2eat.screens.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.what2eat.R

val Lemon = FontFamily(Font(R.font.lemon))

val mainFont = FontFamily(
    Font(R.font.main_font)
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleMedium = TextStyle(
        fontFamily = Lemon,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    )
)
