package id.antasari.p7_modern_ui_230104040079.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp), // Standar untuk Card & Button
    large = RoundedCornerShape(24.dp),  // Untuk Container besar/Bottom Sheet
    extraLarge = RoundedCornerShape(32.dp)
)