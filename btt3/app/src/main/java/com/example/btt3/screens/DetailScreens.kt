package com.example.btt3.screens
import androidx.compose.ui.tooling.preview.Preview
import com.example.btt3.ui.theme.Btt3Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.btt3.R
import androidx.compose.ui.layout.ContentScale


// ---------------------- 1. Text Detail Screen ----------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextDetailScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Text Detail",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(200.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "The quick ", style = MaterialTheme.typography.displaySmall)
                Text(
                    text = "Brown",
                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFF964B00) // Màu Nâu
                )
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "fox jumps ", style = MaterialTheme.typography.displaySmall)
                Text(
                    text = "over",
                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
                )
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "the ", style = MaterialTheme.typography.displaySmall)
                Text(
                    text = "lazy",
                    style = MaterialTheme.typography.displaySmall.copy(fontStyle = FontStyle.Italic)
                )
                Text(text = " dog.", style = MaterialTheme.typography.displaySmall)
            }
        }
    }
}
// ---------------------- 2. Image Detail Screen ----------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageDetailScreen() {
    // URL ảnh đầu tiên từ slide (Giữ lại URL text để hiển thị)
    val imageUrl = "https://giaothongvantaipchcm.edu.vn/wp-content/uploads/2025/01/Logo-GTVT.png"

    // Tùy chỉnh TopAppBar
    val titleColor = MaterialTheme.colorScheme.primary
    val titleStyle = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Images", style = titleStyle) },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    titleContentColor = titleColor
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // --- HÌNH ẢNH 1: Từ URL (Giả lập bằng Image Cục bộ) ---
            Image(
                // 💡 SỬ DỤNG LẠI HÌNH ẢNH CỤC BỘ CHO VỊ TRÍ ẢNH TẢI TỪ MẠNG
                painter = painterResource(id = R.drawable.img1),
                contentDescription = "Hình ảnh tải từ mạng (Placeholder)",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 200.dp),
                contentScale = ContentScale.Crop
            )

            // Văn bản URL
            Text(
                text = imageUrl,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Spacer(Modifier.height(16.dp))

            // --- HÌNH ẢNH 2: Từ Resource Cục bộ (In app) ---
            Image(
                // 💡 HÌNH ẢNH CỤC BỘ THỨ HAI
                painter = painterResource(id = R.drawable.imgg),
                contentDescription = "Cơ sở vật chất của trường",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 200.dp),
                contentScale = ContentScale.Crop
            )

            // Văn bản mô tả
            Text("In app",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp))
        }
    }
}
// ---------------------- 3. TextField Detail Screen ----------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDetailScreen() {
    var textFieldValue by remember { mutableStateOf("") }
    val titleColor = MaterialTheme.colorScheme.primary
    val titleStyle = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("TextField", style = titleStyle) },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    titleContentColor = titleColor
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(300.dp))
            OutlinedTextField(
                value = textFieldValue,
                onValueChange = { newValue -> textFieldValue = newValue },
                label = { Text("Thông tin nhập") },
                modifier = Modifier.width(300.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Tự động cập nhật dữ liệu theo textfield",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error
            )
            Text(
                text = textFieldValue.ifEmpty { "..." },
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

// ---------------------- 4. Row Layout Screen ----------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowLayoutScreen() {
    val titleColor = MaterialTheme.colorScheme.primary
    val titleStyle = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Row Layout", style = titleStyle) },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    titleContentColor = titleColor
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier.widthIn(max = 350.dp)
            ) {
                repeat(4) { rowIndex ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        repeat(4) { columnIndex ->
                            // Tạo 4 ô vuông trong mỗi hàng
                            val color = if (columnIndex % 2 == 0) Color(0xFF64B5F6) else Color(0xFF42A5F5)
                            Box(
                                modifier = Modifier
                                    .weight(1f) // Chiếm đều 1/4 chiều rộng còn lại
                                    .height(50.dp)
                                    .padding(horizontal = 4.dp)
                                    .background(color, RoundedCornerShape(8.dp)
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}
// ---------------------- BẢN XEM TRƯỚC ----------------------
// Bản xem trước cho Text Detail
@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "3. Text Detail", showBackground = true, group = "ALL SCREENS")
@Composable
fun Preview3_TextDetailScreen() {
    Btt3Theme {
        TextDetailScreen()
    }
}

// Bản xem trước cho Image Detail
@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "4. Image Detail", showBackground = true, group = "ALL SCREENS")
@Composable
fun Preview4_ImageDetailScreen() {
    Btt3Theme {
        ImageDetailScreen()
    }
}

// Bản xem trước cho TextField Detail
@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "5. TextField Detail", showBackground = true, group = "ALL SCREENS")
@Composable
fun Preview5_TextFieldDetailScreen() {
    Btt3Theme {
        TextFieldDetailScreen()
    }
}

// Bản xem trước cho Row Layout
@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "6. Row Layout", showBackground = true, group = "ALL SCREENS")
@Composable
fun Preview6_RowLayoutScreen() {
    Btt3Theme {
        RowLayoutScreen()
    }
}