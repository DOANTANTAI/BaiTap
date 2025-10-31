package com.example.smarttasks.ui.forgetpassword
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttasks.R
import com.example.smarttasks.ui.theme.SmartTasksTheme
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavScreen(
    viewModel: ForgetPasswordViewModel,
    onNextClicked: () -> Unit
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = { TopAppBar(title = {}) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                painter = painterResource(id = R.drawable.logo_uth),
                contentDescription = "UTH Logo",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "SmartTasks",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(40.dp))

            Text(text = "Quên Mật Khẩu?", style = MaterialTheme.typography.titleLarge)
            Text(text = "Nhập Email, chúng tôi sẽ gửi mã xác nhận.", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = state.email,
                onValueChange = viewModel::updateEmail,
                label = { Text("Email của bạn") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    viewModel.sendVerificationCode()
                    onNextClicked()
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                enabled = state.email.isNotBlank() && !state.isLoading
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

@Preview(showBackground = true, name = "Nav Screen Preview")
@Composable
fun NavScreenPreview() {
    val mockViewModel: ForgetPasswordViewModel = viewModel()
     mockViewModel.updateEmail("test@uth.edu.vn")

    SmartTasksTheme {
        NavScreen(
            viewModel = mockViewModel,
            onNextClicked = {}
        )
    }
}