package id.antasari.p7_modern_ui_230104040079

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
// Import Screens & Theme
import id.antasari.p7_modern_ui_230104040079.ui.screen.HomeScreen
import id.antasari.p7_modern_ui_230104040079.ui.screen.LoginScreen
import id.antasari.p7_modern_ui_230104040079.ui.screen.ProfileScreen
import id.antasari.p7_modern_ui_230104040079.ui.screen.SettingsScreen
import id.antasari.p7_modern_ui_230104040079.ui.theme.HabitConnectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // State untuk Tema (Dark/Light) disimpan di level paling atas
            var isDarkTheme by remember { mutableStateOf(false) }

            HabitConnectTheme(darkTheme = isDarkTheme) {
                MainApp(
                    isDarkTheme = isDarkTheme,
                    onThemeChanged = { isDarkTheme = it }
                )
            }
        }
    }
}

// Data Class untuk item menu bawah
data class BottomNavItem(
    val label: String,
    val route: String,
    val icon: ImageVector
)

@Composable
fun MainApp(
    isDarkTheme: Boolean,
    onThemeChanged: (Boolean) -> Unit
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Daftar halaman untuk Bottom Bar
    val bottomNavItems = listOf(
        BottomNavItem("Home", "home", Icons.Default.Home),
        BottomNavItem("Profile", "profile", Icons.Default.AccountCircle),
        BottomNavItem("Settings", "settings", Icons.Default.Settings),
    )

    Scaffold(
        bottomBar = {
            // Bottom Bar HANYA muncul jika BUKAN di halaman Login
            if (currentRoute != "login") {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.primary
                ) {
                    bottomNavItems.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                            label = { Text(item.label) },
                            selected = currentRoute == item.route,
                            onClick = {
                                navController.navigate(item.route) {
                                    // Agar tidak menumpuk halaman saat back button ditekan
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                indicatorColor = MaterialTheme.colorScheme.secondaryContainer,
                                unselectedIconColor = Color.Gray
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        // Pengaturan Navigasi Halaman
        NavHost(
            navController = navController,
            startDestination = "login", // Awal aplikasi buka Login dulu
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("login") {
                LoginScreen(
                    onLoginSuccess = {
                        // Pindah ke Home dan hapus Login dari backstack (agar tidak bisa back ke login)
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )
            }
            composable("home") {
                HomeScreen()
            }
            composable("profile") {
                ProfileScreen()
            }
            composable("settings") {
                SettingsScreen(
                    isDarkTheme = isDarkTheme,
                    onThemeChanged = onThemeChanged
                )
            }
        }
    }
}