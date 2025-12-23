package com.skrpld.uikit.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.skrpld.uikit.R

val Roboto = FontFamily(
    Font(R.font.roboto_variable_font),
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = Roboto,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.W700
    ),
    titleMedium = TextStyle(
        fontFamily = Roboto,
        fontSize = 20.sp,
        lineHeight = 28.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Roboto,
        fontSize = 17.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W500
    ),
    headlineLarge = TextStyle(
        fontFamily = Roboto,
        fontSize = 16.sp,
        lineHeight = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Roboto,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W400
    ),
    labelLarge = TextStyle(
        fontFamily = Roboto,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W400,
        color = Description
    ),
    labelMedium = TextStyle(
        fontFamily = Roboto,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        color = Description
    ),
)