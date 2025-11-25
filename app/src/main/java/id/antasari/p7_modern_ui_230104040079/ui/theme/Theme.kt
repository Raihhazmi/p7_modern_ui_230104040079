package id.antasari.p7_modern_ui_230104040079.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Definisi Skema Warna Gelap
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = OnPrimaryDark,
    background = DarkBackground,
    surface = DarkSurface,
    onSurface = WhitePure
)

// Definisi Skema Warna Terang
private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = OnPrimaryLight,
    background = WhitePure,
    surface = WhitePure, // Card background di light mode biasanya putih
    onSurface = BlackText,
    secondaryContainer = ContainerLight // Untuk background TextField
)

@Composable
fun HabitConnectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color dimatikan dulu agar warna merah (branding) tetap dominan
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Mengatur warna Status Bar agar menyatu dengan aplikasi
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}