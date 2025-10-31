package com.example.btvn_t2
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
class MainActivity : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var textViewResult: TextView
    private lateinit var buttonCheck: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextName = findViewById(R.id.editText_Name)
        editTextAge = findViewById(R.id.editText_Age)
        textViewResult = findViewById(R.id.textView_Result)
        buttonCheck = findViewById(R.id.button_Check)
        buttonCheck.setOnClickListener {
            checkAgeCategory()
        }
    }
    private fun checkAgeCategory() {
        val name = editTextName.text.toString().trim()
        val ageStr = editTextAge.text.toString().trim()
        if (name.isEmpty()) {
            editTextName.error = "Vui lòng nhập Họ và tên"
            textViewResult.text = "Vui lòng điền đầy đủ thông tin."
            return
        }
        if (ageStr.isEmpty()) {
            editTextAge.error = "Vui lòng nhập Tuổi"
            textViewResult.text = "Vui lòng điền đầy đủ thông tin."
            return
        }
        val age = ageStr.toIntOrNull()
        if (age == null) {
            editTextAge.error = "Tuổi phải là một số hợp lệ"
            textViewResult.text = "Lỗi: Tuổi phải là giá trị số."
            return
        }
        val category = when (age) {
            in 6..65 -> "Người lớn"
            in 2..5 -> "Trẻ em"
            in 0..1 -> "Em bé"
            in 66..Int.MAX_VALUE -> "Người già"
            else -> "Không xác định"
        }
        val resultMessage = "Họ tên: $name\nTuổi: $age\nPhân loại: $category"
        textViewResult.text = resultMessage
        Toast.makeText(this, "Đã kiểm tra!", Toast.LENGTH_SHORT).show()
    }
}