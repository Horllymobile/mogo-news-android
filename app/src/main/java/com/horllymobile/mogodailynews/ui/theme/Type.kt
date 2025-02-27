package com.horllymobile.mogodailynews.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.horllymobile.mogodailynews.R

// Set of Material typography styles to start with
val loraFamily = FontFamily(
    Font(R.font.lora_regular, FontWeight.W400),
    Font(R.font.lora_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.lora_semibold_italic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.lora_medium, FontWeight.Medium),
    Font(R.font.lora_semi_bold, FontWeight.SemiBold),
    Font(R.font.lora_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.lora_bold, FontWeight.Bold)
)

val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = loraFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = loraFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = loraFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = loraFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = loraFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = loraFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = loraFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = loraFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = loraFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = loraFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = loraFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = loraFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = loraFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = loraFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = loraFamily),
)
