package com.example.smarttasks.ui.forgetpassword
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmScreen(
    viewModel: ForgetPasswordViewModel,
    onBackToLogin: () -> Unit
) {
    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { TopAppBar(title = { }, navigationIcon = { IconButton(onClick = onBackToLogin) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Quay lại") }}) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Text(text = "Xác nhận", style = MaterialTheme.typography.titleLarge)
            Text(text = "Mật khẩu đã được thiết lập lại thành công!", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(
                value = state.email,
                onValueChange = { /* read only */ },
                label = { Text("Email") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = "********",
                onValueChange = { /* read only */ },
                label = { Text("Mật khẩu mới đã được lưu") },
                readOnly = true,
                visualTransformation = PasswordVisualTransformation(),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = "Mật khẩu đang bị ẩn",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Đã reset mật khẩu thành công cho ${state.email}!",
                            actionLabel = "Đóng",
                            duration = SnackbarDuration.Short
                        )
                        onBackToLogin()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
            ) {
                Text("HOÀN TẤT (Summit)")
            }
        }
    }
}