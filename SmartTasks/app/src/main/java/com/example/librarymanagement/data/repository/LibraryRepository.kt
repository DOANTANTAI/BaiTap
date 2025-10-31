package com.example.librarymanagement.data.repository
import com.example.librarymanagement.data.model.Book
import com.example.librarymanagement.data.model.Student
class LibraryRepository {
    private val bookList = mutableListOf<Book>()
    private val studentList = mutableListOf<Student>()
    init {
        loadInitialData()
    }
    private fun loadInitialData() {
        bookList.add(Book("B001", "Sách 01", "Nguyễn Văn A"))
        bookList.add(Book("B002", "Sách 02", "Trần Thị B"))
        bookList.add(Book("B003", "Sách 03", "Lê Văn C", isAvailable = false, borrowerId = "S003"))
        bookList.add(Book("B004", "Sách 04", "Phạm Văn D"))
        studentList.add(Student("S001", "Nguyen Van A", "CNTT K1"))
        studentList.add(Student("S002", "Nguyen Thi B", "CNTT K2"))
        studentList.add(Student(
            "S003",
            "Nguyen Van C",
            "QTKD K3",
            mutableListOf(bookList.find { it.bookId == "B003" }!!)
        ))
    }
    fun getStudents(): List<Student> = studentList.toList()
    fun getBooks(): List<Book> = bookList.toList()
    fun findStudentById(studentId: String): Student? = studentList.find { it.studentId == studentId }
    fun borrowBooks(studentId: String, booksToBorrow: List<Book>): Boolean {
        val currentStudent = studentList.find { it.studentId == studentId }
        if (currentStudent == null || booksToBorrow.isEmpty()) return false
        var success = false
        booksToBorrow.forEach { book ->
            val foundBook = bookList.find { it.bookId == book.bookId }
            if (foundBook != null && foundBook.isAvailable) {
                foundBook.isAvailable = false
                foundBook.borrowerId = currentStudent.studentId
                currentStudent.currentBorrowedBooks.add(foundBook)
                success = true
            }
        }
        return true
    }
    fun addStudent(studentName: String, className: String): Student {
        val newId = "S" + (studentList.size + 1).toString().padStart(3, '0')
        val newStudent = Student(newId, studentName, className)
        studentList.add(newStudent)
        return newStudent
    }
    fun addBook(title: String, author: String): Book {
        val newId = "B" + (bookList.size + 1).toString().padStart(3, '0')
        val newBook = Book(newId, title, author, isAvailable = true)
        bookList.add(newBook)
        return newBook
    }
}