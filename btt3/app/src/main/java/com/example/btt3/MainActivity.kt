package com.example.btt3
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.btt3.ui.theme.Btt3Theme
import com.example.btt3.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Sử dụng Theme của dự án
            Btt3Theme {
                val navController = rememberNavController()

                // NavHost định nghĩa các màn hình (routes)
                NavHost(
                    navController = navController,
                    startDestination = "home_screen"
                ) {
                    composable("home_screen") {
                        HomeScreen(navController)
                    }
                    composable("components_list") {
                        ComponentsListScreen(navController)
                    }
                    // Các màn hình chi tiết
                    composable("text_detail") { TextDetailScreen() }
                    composable("image_detail") { ImageDetailScreen() }
                    composable("textfield_detail") { TextFieldDetailScreen() }
                    composable("row_layout") { RowLayoutScreen() }
                }
            }
        }
    }
}