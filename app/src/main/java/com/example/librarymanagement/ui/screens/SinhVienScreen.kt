package com.example.librarymanagement.ui.screens
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.librarymanagement.data.model.Student
import com.example.librarymanagement.ui.viewmodel.LibraryViewModel
import androidx.compose.ui.tooling.preview.Preview
import com.example.librarymanagement.ui.theme.LibraryManagementTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import com.example.librarymanagement.ui.components.AddStudentDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SinhVienScreen(
    onStudentSelected: () -> Unit,
    viewModel: LibraryViewModel = viewModel()
) {
    var showAddStudentDialog by remember { mutableStateOf(false) }
    var triggerUpdate by remember { mutableStateOf(false) }
    val students = viewModel.getStudentList()
    var studentForDetail by remember { mutableStateOf<Student?>(null) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Danh Sách Sinh Viên", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { showAddStudentDialog = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Thêm Sinh viên")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp)) {
            Spacer(Modifier.height(16.dp))
            LazyColumn {
                items(students) { student ->
                    StudentItem(student = student) {
                        studentForDetail = student
                    }
                    Divider()
                }
            }
        }
    }
    studentForDetail?.let { student ->
        BorrowedBooksDialog(
            student = student,
            onDismiss = { studentForDetail = null }
        )
    }

    // Hiển thị Dialog Thêm Sinh Viên
    if (showAddStudentDialog) {
        AddStudentDialog(
            onDismiss = { showAddStudentDialog = false },
            onAdd = { name, className ->
                viewModel.addNewStudent(name, className)
                showAddStudentDialog = false
                triggerUpdate = !triggerUpdate
            }
        )
    }
}
@Composable
fun BorrowedBooksDialog(
    student: Student,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Sách Đã Mượn - ${student.name}") },
        text = {
            val borrowedBooks = student.currentBorrowedBooks

            if (borrowedBooks.isEmpty()) {
                Text("Sinh viên này hiện không mượn quyển sách nào.")
            } else {
                LazyColumn(modifier = Modifier.heightIn(max = 300.dp)) {
                    item {
                        Text(
                            "Tổng cộng: ${borrowedBooks.size} quyển",
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                    items(borrowedBooks) { book ->
                        Column(modifier = Modifier.padding(vertical = 4.dp)) {
                            Text(book.title, fontWeight = FontWeight.Medium)
                            Text("Tác giả: ${book.author}", style = MaterialTheme.typography.bodySmall)
                            Divider()
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Đóng")
            }
        }
    )
}

@Composable
fun StudentItem(student: Student, onSelect: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onSelect)
            .padding(vertical = 12.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(student.name, style = MaterialTheme.typography.titleMedium)
            Text(
                "Mã SV: ${student.studentId} | Lớp: ${student.className}",
                style = MaterialTheme.typography.bodySmall
            )
        }
        Text(
            "Sách đang mượn: ${student.currentBorrowedBooks.size}",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
@Preview(showBackground = true)
@Composable
fun SinhVienScreenPreview() {
    LibraryManagementTheme {
        SinhVienScreen(
            onStudentSelected = {}
        )
    }
}