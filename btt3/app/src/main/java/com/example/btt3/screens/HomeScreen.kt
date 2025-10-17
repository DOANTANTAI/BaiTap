
package com.example.btt3.screens
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.btt3.R // Cần để truy cập drawable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.btt3.ui.theme.Btt3Theme // Điều chỉnh tên Theme của bạn
import androidx.compose.ui.text.style.TextAlign
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 32.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = "Jetpack Compose Logo",
                    modifier = Modifier
                        .size(300.dp)
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = "Jetpack Compose is modern UI toolkit for building native Android applications using declarative programming approach.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            Button(
                onClick = { navController.navigate("components_list") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("I'm ready")
            }

        }
    }
}
@Preview(name = "1. Home Screen", showBackground = true, group = "ALL SCREENS")
@Composable
fun Preview1_HomeScreen() {
    val navController = rememberNavController()
    Btt3Theme {
        HomeScreen(navController = navController)
    }
}