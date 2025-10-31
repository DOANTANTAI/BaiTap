package com.example.smarttasks.ui.login
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttasks.R
import com.example.smarttasks.data.AuthManager
import com.example.smarttasks.ui.theme.SmartTasksTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.remember

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    authManager: AuthManager,
    onBackClicked: () -> Unit
) {
    val user = authManager.getCurrentUser()

    Scaffold(topBar = { TopAppBar(title = { Text("Profile") }) }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile1),
                contentDescription = "User Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Thông tin Profile
            OutlinedTextField(
                value = user?.displayName ?: "Melisa Peters",
                onValueChange = {},
                label = { Text("Name") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = user?.email ?: "melissapeters@gmail.com",
                onValueChange = {},
                label = { Text("Email") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "23/05/1985",
                onValueChange = {},
                label = { Text("Date of Birth") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onBackClicked,
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Back (Logout)")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val context = LocalContext.current
    val mockAuthManager = remember { AuthManager(context) }
    SmartTasksTheme {
        ProfileScreen(authManager = mockAuthManager, onBackClicked = {})
    }
}