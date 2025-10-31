package com.example.smarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.smarttasks.ui.navigation.NavGraph
import com.example.smarttasks.ui.theme.SmartTasksTheme // Đảm bảo theme của bạn đã được tạo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Thay thế bằng tên Theme của bạn
            SmartTasksTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph() // Khởi chạy điều hướng
                }
            }
        }
    }
}