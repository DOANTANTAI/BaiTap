package com.example.smarttasks.ui.forgetpassword
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smarttasks.ui.theme.SmartTasksTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordScreen(
    viewModel: ForgetPasswordViewModel,
    onBackClicked: () -> Unit,
    onNextClicked: () -> Unit
) {
    val state = viewModel.state.value
    var passwordInput by remember { mutableStateOf(state.newPassword) }
    var confirmPasswordInput by remember { mutableStateOf("") }
    val isPasswordValid = passwordInput.length >= 6 && passwordInput == confirmPasswordInput
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    Scaffold(
        topBar = { TopAppBar(title = {}, navigationIcon = { IconButton(onClick = onBackClicked) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Quay lại") }}) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Text(text = "Tạo Mật Khẩu Mới", style = MaterialTheme.typography.titleLarge)
            Text(text = "Mật khẩu mới phải khác với mật khẩu đã sử dụng trước đó.", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(
                value = passwordInput,
                onValueChange = { passwordInput = it },
                label = { Text("Mật khẩu (ít nhất 6 ký tự)") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (passwordVisible) "Ẩn mật khẩu" else "Hiện mật khẩu"
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = description)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = confirmPasswordInput,
                onValueChange = { confirmPasswordInput = it },
                label = { Text("Xác nhận Mật khẩu") },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (confirmPasswordVisible) "Ẩn xác nhận mật khẩu" else "Hiện xác nhận mật khẩu"
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(imageVector = image, contentDescription = description)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    viewModel.updateNewPassword(passwordInput)
                    viewModel.resetPassword()
                    onNextClicked()
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                enabled = isPasswordValid && !state.isLoading
            ) {
                Text("Tiếp theo")
            }
        }
    }
}

@Preview(showBackground = true, name = "Reset Password Screen Preview")
@Composable
fun ResetPasswordScreenPreview() {
    val mockViewModel: ForgetPasswordViewModel = viewModel()
    mockViewModel.updateEmail("test@uth.edu.vn")

    SmartTasksTheme {
        ResetPasswordScreen(
            viewModel = mockViewModel,
            onBackClicked = {},
            onNextClicked = {}
        )
    }
}