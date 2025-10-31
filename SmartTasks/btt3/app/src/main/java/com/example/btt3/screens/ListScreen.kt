package com.example.btt3.screens
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.btt3.ui.theme.Btt3Theme
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentsListScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val components = listOf(
        Triple("Text", "Displays text", "text_detail"),
        Triple("Image", "Displays an image", "image_detail"),
        Triple("TextField", "Input field for text", "textfield_detail"),
        Triple("PasswordField", "Input field for passwords", "textfield_detail"),
        Triple("Column", "Arranges elements vertically", "row_layout"),
        Triple("Row", "Arranges elements horizontally", "row_layout")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "UI Components List",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge.copy( fontWeight = FontWeight.Bold)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(top = 16.dp)
            .verticalScroll(scrollState)) {
            Text(text = "Display", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
            components.subList(0, 2).forEach { (name, desc, route) ->
                ListItemComposable(name, desc, route, navController)
            }
            Text(text = "Input", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
            components.subList(2, 4).forEach { (name, desc, route) ->
                ListItemComposable(name, desc, route, navController)
            }
            Text(text = "Layout", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
            components.subList(4, 6).forEach { (name, desc, route) ->
                ListItemComposable(name, desc, route, navController)
            }
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFFFBE4E4)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Tự tìm hiểu", style = MaterialTheme.typography.titleMedium, color = Color.Red)
                    Text("Tìm ra tất cả các thành phần UI cơ bản",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Red)
                }
            }
        }
    }
}

@Composable
fun ListItemComposable(name: String, description: String, route: String, navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { navController.navigate(route) },
        shape = RoundedCornerShape(8.dp),
        color = Color(0xFFE3F2FD)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            /*Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Go to detail"
            )*/
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "2. Components List", showBackground = true, group = "ALL SCREENS")
@Composable
fun Preview2_ComponentsListScreen() {
    val navController = rememberNavController()
    Btt3Theme {
        ComponentsListScreen(navController = navController)
    }
}