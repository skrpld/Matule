package com.skrpld.matule.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

val Roboto = FontFamily()

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = Roboto,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.33.em
    ),
    titleMedium = TextStyle(
        fontFamily = Roboto,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.38.em
    ),
    titleSmall = TextStyle(
        fontFamily = Roboto,
        fontSize = 17.sp,
        lineHeight = 24.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = Roboto,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.32).em
    ),
    bodyLarge = TextStyle(
        fontFamily = Roboto,
        fontSize = 15.sp,
        lineHeight = 20.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Roboto,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Roboto,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
)