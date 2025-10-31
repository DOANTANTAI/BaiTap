package com.example.smarttasks.ui.forgetpassword
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttasks.ui.theme.SmartTasksTheme
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationScreen(
    viewModel: ForgetPasswordViewModel,
    onBackClicked: () -> Unit,
    onNextClicked: () -> Unit
) {
    val state = viewModel.state.value
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = { IconButton(onClick = onBackClicked) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Quay lại") } }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Text(text = "Xác Thực Mã", style = MaterialTheme.typography.titleLarge)
            Text(text = "Mã xác nhận đã gửi đến ${state.email}", style = MaterialTheme.typography.bodySmall)

            if (state.errorMessage != null) {
                Text(state.errorMessage, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 16.dp))
            }
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(
                value = state.verificationCode,
                onValueChange = viewModel::updateVerificationCode,
                label = { Text("Nhập mã 4 số") },
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    viewModel.verifyCode(onNextClicked)
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                enabled = state.verificationCode.length == 4 && !state.isLoading
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Text("Tiếp theo")
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Verification Screen Preview")
@Composable
fun VerificationScreenPreview() {
    val mockViewModel: ForgetPasswordViewModel = viewModel()
    mockViewModel.updateEmail("test@uth.edu.vn")
    SmartTasksTheme {
        VerificationScreen(
            viewModel = mockViewModel,
            onBackClicked = {},
            onNextClicked = {}
        )
    }
}