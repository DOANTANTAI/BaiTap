package com.example.smarttasks.ui.navigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smarttasks.data.AuthManager
import com.example.smarttasks.ui.forgetpassword.*
import com.example.smarttasks.ui.login.LoginScreen
import com.example.smarttasks.ui.login.ProfileScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    val authManager = remember { AuthManager(context) }
    // Kiểm tra xem người dùng đã đăng nhập chưa
    val startDestination = if (authManager.getCurrentUser() != null) {
        Screens.Profile.route
    } else {
        Screens.Login.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screens.Login.route) {
            LoginScreen(
                authManager = authManager,
                onLoginSuccess = {
                    navController.navigate(Screens.Profile.route) {
                        popUpTo(Screens.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screens.Profile.route) {
            ProfileScreen(
                authManager = authManager,
                onBackClicked = {
                    authManager.signOut()
                    navController.navigate(Screens.Login.route) {
                        popUpTo(Screens.Profile.route) { inclusive = true }
                    }
                }
            )
        }

        // Màn hình 1: NavScreen
        composable(Screens.Nav.route) {
            val forgetPasswordViewModel: ForgetPasswordViewModel = viewModel()
            NavScreen(
                viewModel = forgetPasswordViewModel,
                onNextClicked = { navController.navigate(Screens.Verification.route) }
            )
        }

        // Màn hình 2: VerificationScreen
        composable(Screens.Verification.route) {
            val forgetPasswordViewModel: ForgetPasswordViewModel = viewModel()
            VerificationScreen(
                viewModel = forgetPasswordViewModel,
                onBackClicked = { navController.popBackStack() },
                onNextClicked = { navController.navigate(Screens.ResetPassword.route) }
            )
        }

        // Màn hình 3: ResetPasswordScreen
        composable(Screens.ResetPassword.route) {
            val forgetPasswordViewModel: ForgetPasswordViewModel = viewModel()
            ResetPasswordScreen(
                viewModel = forgetPasswordViewModel,
                onBackClicked = { navController.popBackStack() },
                onNextClicked = { navController.navigate(Screens.Confirm.route) }
            )
        }

        // Màn hình 4: ConfirmScreen
        composable(Screens.Confirm.route) {
            val forgetPasswordViewModel: ForgetPasswordViewModel = viewModel()
            ConfirmScreen(
                viewModel = forgetPasswordViewModel,
                onBackToLogin = {
                    // Quay về Login sau khi hoàn tất luồng
                    navController.navigate(Screens.Login.route) {
                        popUpTo(Screens.Login.route) { inclusive = true }
                    }
                }
            )
        }
    }
}