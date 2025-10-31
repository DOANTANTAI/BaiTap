package com.example.smarttasks.ui.forgetpassword
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ForgetPasswordViewModel : ViewModel() {
    private val _state = mutableStateOf(ForgetPasswordState())
    val state: State<ForgetPasswordState> = _state
    fun updateEmail(newEmail: String) {
        _state.value = _state.value.copy(email = newEmail.trim())
    }
    fun sendVerificationCode() {
        _state.value = _state.value.copy(
            isLoading = true,
            errorMessage = null
        )
        println("Sending code to: ${_state.value.email}")
        _state.value = _state.value.copy(
            isLoading = false,
            isCodeSent = true
        )
    }

    fun updateVerificationCode(newCode: String) {
        if (newCode.length <= 4) {
            _state.value = _state.value.copy(
                verificationCode = newCode,
                errorMessage = null
            )
        }
    }

    fun verifyCode(onSuccess: () -> Unit) {
        _state.value = _state.value.copy(isLoading = true)
        if (_state.value.verificationCode == "1234") {
            _state.value = _state.value.copy(
                isLoading = false,
                errorMessage = null
            )
            onSuccess()
        } else {
            _state.value = _state.value.copy(
                isLoading = false,
                errorMessage = "Mã xác nhận không đúng. Vui lòng thử lại. (Mẫu: 1234)"
            )
        }
    }

    fun updateNewPassword(password: String) {
        _state.value = _state.value.copy(newPassword = password)
    }

    fun resetPassword() {
        _state.value = _state.value.copy(isLoading = true)
        // Thực hiện logic reset mật khẩu qua API
        println("Password reset for ${_state.value.email} with new password.")
        _state.value = _state.value.copy(isLoading = false)
    }
}