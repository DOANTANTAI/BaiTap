package com.example.smarttasks.ui.forgetpassword

data class ForgetPasswordState(
    val email: String = "",
    val verificationCode: String = "",
    val newPassword: String = "",
    val isCodeSent: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)