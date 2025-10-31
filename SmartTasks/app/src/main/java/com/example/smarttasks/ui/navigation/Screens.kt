package com.example.smarttasks.ui.navigation

sealed class Screens(val route: String) {
    object Nav : Screens("nav_screen")
    object Verification : Screens("verification_screen")
    object ResetPassword : Screens("reset_password_screen")
    object Confirm : Screens("confirm_screen")
    object Login : Screens("login_screen")
    object Profile : Screens("profile_screen")
}