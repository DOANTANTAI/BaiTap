package com.example.librarymanagement.ui.screens
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.librarymanagement.data.model.Book
import com.example.librarymanagement.ui.viewmodel.LibraryViewModel
import androidx.compose.ui.tooling.preview.Preview
import com.example.librarymanagement.ui.theme.LibraryManagementTheme
import android.widget.Toast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DSSachScreen(viewModel: LibraryViewModel = viewModel()) {
    val allBooks by viewModel.allBooks.collectAsStateWithLifecycle()
    var showAddBookDialog by remember { mutableStateOf(false) }
    var selectedBookForInfo by remember { mutableStateOf<Book?>(null) }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Danh sách Toàn bộ Sách", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { showAddBookDialog = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Thêm Sách")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 16.dp)) {
            Spacer(Modifier.height(8.dp))
            LazyColumn {
                items(allBooks) { book ->
                    BookDetailItem(
                        book = book,
                        viewModel = viewModel,
                        onClick = { clickedBook ->
                            if (!clickedBook.isAvailable && clickedBook.borrowerId != null) {
                                selectedBookForInfo = clickedBook // Mở Dialog
                            } else {
                                Toast.makeText(context, "Sách này đang khả dụng.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
    if (showAddBookDialog) {
        AddBookDialog(
            onDismiss = { showAddBookDialog = false },
            onAdd = { title, author ->
                viewModel.addNewBook(title, author)
                showAddBookDialog = false
            }
        )
    }
    selectedBookForInfo?.let { book ->
        val borrower = viewModel.getStudentList().find { it.studentId == book.borrowerId }
        val borrowerClass = borrower?.className ?: "Không rõ"
        BorrowerInfoDialog(
            bookTitle = book.title,
            borrowerName = borrower?.name ?: "Không tìm thấy người mượn (ID: ${book.borrowerId})",
            borrowerClass = borrowerClass,
            onDismiss = {
                selectedBookForInfo = null
            }
        )
    }
}
@Composable
fun BookDetailItem(book: Book, viewModel: LibraryViewModel, onClick: (Book) -> Unit) {
    val statusColor = if (book.isAvailable) Color(0xFF4CAF50) else Color(0xFFF44336)

    // Nếu sách đã mượn, tìm tên người mượn để hiển thị
    val borrower = if (!book.isAvailable) {
        viewModel.getStudentList().find { it.studentId == book.borrowerId }
    } else {
        null
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onClick(book) })
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(book.title, style = MaterialTheme.typography.titleMedium)
            Text("Tác giả: ${book.author}", style = MaterialTheme.typography.bodySmall)
            Text("Mã sách: ${book.bookId}", style = MaterialTheme.typography.bodySmall)
            if (borrower != null) {
                Spacer(Modifier.height(4.dp))
                Text(
                    "Người mượn: ${borrower.name}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
            }
        }
        Text(
            if (book.isAvailable) "Khả dụng" else "Đã mượn",
            color = statusColor,
            fontWeight = FontWeight.SemiBold
        )
    }
}
@Composable
fun AddBookDialog(onDismiss: () -> Unit, onAdd: (title: String, author: String) -> Unit) {
    var bookTitle by remember { mutableStateOf("") }
    var bookAuthor by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Thêm Sách Mới") },
        text = {
            Column {
                OutlinedTextField(
                    value = bookTitle,
                    onValueChange = { bookTitle = it },
                    label = { Text("Tên Sách (Title)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = bookAuthor,
                    onValueChange = { bookAuthor = it },
                    label = { Text("Tác Giả (Author)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (bookTitle.isNotBlank() && bookAuthor.isNotBlank()) {
                        onAdd(bookTitle.trim(), bookAuthor.trim())
                    }
                }
            ) {
                Text("Thêm")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Hủy")
            }
        }
    )
}
@Composable
fun BorrowerInfoDialog(
    bookTitle: String,
    borrowerName: String,
    borrowerClass: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Thông tin Mượn Sách") },
        text = {
            Column {
                Text("Sách: $bookTitle", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))

                // HIỂN THỊ TÊN NGƯỜI MƯỢN
                Text("Người mượn:", style = MaterialTheme.typography.bodyMedium)
                Text(borrowerName, style = MaterialTheme.typography.headlineSmall)

                // BỔ SUNG: HIỂN THỊ LỚP
                Spacer(Modifier.height(8.dp))
                Text("Lớp:", style = MaterialTheme.typography.bodyMedium)
                Text(borrowerClass, style = MaterialTheme.typography.headlineSmall)
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Đóng")
            }
        }
    )
}
@Preview(showBackground = true)
@Composable
fun DSSachScreenPreview() {
    LibraryManagementTheme {
        DSSachScreen()
    }
}