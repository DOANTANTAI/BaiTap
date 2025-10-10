package com.example.th2_1

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt // Dùng KTX để chuyển đổi màu

class MainActivity : AppCompatActivity() {

    // Khai báo các Views
    private lateinit var etNumberInput: EditText
    private lateinit var tvErrorMessage: TextView
    private lateinit var llListContainer: LinearLayout
    private lateinit var scrollViewList: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các Views bằng findViewById
        // et_number_input là ID của TextInputEditText bên trong TextInputLayout
        etNumberInput = findViewById(R.id.et_number_input)
        tvErrorMessage = findViewById(R.id.tv_error_message)
        llListContainer = findViewById(R.id.ll_list_container)
        scrollViewList = findViewById(R.id.scroll_view_list)
    }

    /**
     * Hàm xử lý sự kiện khi nút "Tạo" được nhấn (android:onClick="onCreateButtonClick").
     */
    fun onCreateButtonClick(view: View) {
        val inputText = etNumberInput.text.toString().trim()
        val number: Int? = inputText.toIntOrNull()

        if (number == null || number <= 0) {
            // Trường hợp lỗi: Hiển thị thông báo
            showErrorState()
        } else {
            // Trường hợp hợp lệ: Tạo danh sách
            generateList(number)
        }
    }

    private fun showErrorState() {
        tvErrorMessage.visibility = View.VISIBLE
        scrollViewList.visibility = View.GONE
        llListContainer.removeAllViews() // Xóa danh sách cũ
    }

    private fun generateList(count: Int) {
        val finalCount = if (count > 100) 100 else count // Giới hạn số lượng

        tvErrorMessage.visibility = View.GONE
        scrollViewList.visibility = View.VISIBLE
        llListContainer.removeAllViews()

        for (i in 1..finalCount) {
            val listItemButton = Button(this).apply {
                // Tạo LayoutParams để căn chỉnh và đặt margin cho Button
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 8, 0, 8)
                }
                text = "$i"
                textSize = 18f

                // Đặt màu nền đỏ đậm (sử dụng toColorInt)
                setBackgroundColor("#FF4C4C".toColorInt())
                setTextColor(Color.WHITE)
            }
            llListContainer.addView(listItemButton)
        }
    }
}