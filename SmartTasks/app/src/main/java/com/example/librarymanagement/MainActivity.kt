package com.example.librarymanagement
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.librarymanagement.ui.navigation.Destinations
import com.example.librarymanagement.ui.navigation.LibraryNavHost
import com.example.librarymanagement.ui.theme.LibraryManagementTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryManagementTheme {
                LibraryApp()
            }
        }
    }
}
data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)
@Composable
fun LibraryApp() {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem(Destinations.QUAN_LY, Icons.Default.Home, "Quản lý"),
        BottomNavItem(Destinations.DS_SACH, Icons.Default.List, "DS Sách"),
        BottomNavItem(Destinations.SINH_VIEN, Icons.Default.Person, "Sinh viên")
    )
    Scaffold(
        bottomBar = { BottomNavigationBar(navController, items) }
    ) { innerPadding ->
        LibraryNavHost(navController, modifier = Modifier.padding(innerPadding))
    }
}
@Composable
fun BottomNavigationBar(navController: NavHostController, items: List<BottomNavItem>) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}