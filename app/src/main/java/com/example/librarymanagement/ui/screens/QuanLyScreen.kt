package com.example.librarymanagement.ui.screens
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.librarymanagement.data.model.Student
import com.example.librarymanagement.ui.components.BookSelectionItem
import com.example.librarymanagement.ui.viewmodel.LibraryViewModel
import androidx.compose.ui.tooling.preview.Preview
import com.example.librarymanagement.ui.theme.LibraryManagementTheme
import com.example.librarymanagement.ui.components.AddStudentDialog
import androidx.compose.material3.HorizontalDivider // Import này không cần thiết nếu dùng Divider()

@Composable
fun QuanLyScreen(
    onNavigateToStudentSelection: () -> Unit,
    viewModel: LibraryViewModel = viewModel()
) {
    val context = LocalContext.current
    val currentStudent by viewModel.currentStudent.collectAsStateWithLifecycle()
    val allBooks by viewModel.allBooks.collectAsStateWithLifecycle()
    val selectedBooks = viewModel.selectedBooksForBorrow.value
    val studentName = currentStudent?.name ?: "Chưa chọn Sinh viên"
    var showStudentSelectionDialog by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Hệ thống Quản lý Thư viện", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Sinh viên:", style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.width(8.dp))
            OutlinedTextField(
                value = studentName,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.weight(1f),
                singleLine = true
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = { showStudentSelectionDialog = true }) {
                Text("Thay đổi")
            }
        }
        Spacer(Modifier.height(24.dp))
        Text("Danh sách sách:", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(8.dp))
        val booksForSelection = allBooks.filter { it.isAvailable }
        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
            if (booksForSelection.isEmpty()) {
                Text("Hiện không còn sách khả dụng để mượn.", color = MaterialTheme.colorScheme.error, modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(contentPadding = PaddingValues(bottom = 8.dp)) {
                    items(booksForSelection) { book ->
                        val isSelected = selectedBooks.any { it.bookId == book.bookId }
                        BookSelectionItem(
                            book = book,
                            isSelected = isSelected,
                            onToggle = viewModel::toggleBookSelection
                        )
                    }
                }
            }
        }
        val borrowedCount = currentStudent?.currentBorrowedBooks?.size ?: 0
        if (borrowedCount == 0 && selectedBooks.isEmpty()) {
            Text(
                "Bạn chưa mượn quyển sách nào. Nhấn 'Thêm' để bắt đầu hành trình đọc sách!",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(vertical = 16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        } else if (borrowedCount > 0 && selectedBooks.isEmpty()) {
            Text(
                "Đã mượn: $borrowedCount quyển. Chọn sách mới để thêm.",
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(vertical = 16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Button(
            onClick = {
                if (currentStudent == null) {
                    Toast.makeText(context, "Vui lòng chọn Sinh viên trước.", Toast.LENGTH_SHORT).show()
                } else if (selectedBooks.isEmpty()) {
                    Toast.makeText(context, "Vui lòng chọn sách để mượn.", Toast.LENGTH_SHORT).show()
                } else {
                    if (viewModel.attemptBorrow()) {
                        Toast.makeText(context, "Mượn sách thành công!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Lỗi khi mượn sách hoặc sách đã hết.", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("Thêm", style = MaterialTheme.typography.titleMedium)
        }
    }
    if (showStudentSelectionDialog) {
        StudentSelectionDialog(
            viewModel = viewModel,
            onDismiss = { showStudentSelectionDialog = false },
            onStudentSelected = { student ->
                viewModel.selectStudent(student.studentId)
                showStudentSelectionDialog = false
            }
        )
    }
}
@Composable
fun StudentSelectionDialog(
    viewModel: LibraryViewModel,
    onDismiss: () -> Unit,
    onStudentSelected: (Student) -> Unit
) {
    val students = viewModel.getStudentList().toList()
    var showAddStudentDialog by remember { mutableStateOf(false) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Chọn Sinh Viên") },
        text = {
            Column(modifier = Modifier.heightIn(max = 300.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { showAddStudentDialog = true }) {
                        Text("Thêm mới Sinh viên")
                        Icon(Icons.Default.Add, contentDescription = "Thêm")
                    }
                }
                Divider()
                LazyColumn {
                    items(students) { student ->
                        Text(
                            text = "${student.name} (${student.className})",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(onClick = { onStudentSelected(student) })
                                .padding(vertical = 10.dp)
                        )
                        Divider()
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
    if (showAddStudentDialog) {
        AddStudentDialog(
            onDismiss = { showAddStudentDialog = false },
            onAdd = { name, className ->
                viewModel.addNewStudent(name, className)
                showAddStudentDialog = false
            }
        )
    }
}
@Preview(showBackground = true)
@Composable
fun QuanLyScreenPreview() {
    LibraryManagementTheme {
        QuanLyScreen(
            onNavigateToStudentSelection = {}
        )
    }
}