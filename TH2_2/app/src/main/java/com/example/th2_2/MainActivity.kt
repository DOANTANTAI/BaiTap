package com.example.th2_2
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt

class MainActivity : AppCompatActivity() {
    private lateinit var etEmailInput: EditText
    private lateinit var tvValidationMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etEmailInput = findViewById(R.id.et_email_input)
        tvValidationMessage = findViewById(R.id.tv_validation_message)
    }

    fun onCheckButtonClick(view: View) {
        val email = etEmailInput.text.toString().trim()
        tvValidationMessage.visibility = View.INVISIBLE
        if (email.isEmpty()) {
            showValidationMessage("Email không hợp lệ", "#FF0000".toColorInt())
            return
        }
        if (!email.contains("@")) {
            showValidationMessage("Email không đúng định dạng", "#FF0000".toColorInt())
            return
        }

        showValidationMessage("Bạn đã nhập email hợp lệ", "#008000".toColorInt())
    }

    private fun showValidationMessage(message: String, color: Int) {
        tvValidationMessage.text = message
        tvValidationMessage.setTextColor(color)
        tvValidationMessage.visibility = View.VISIBLE
    }
}