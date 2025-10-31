package com.example.librarymanagement.data.model
data class Student(
    val studentId: String,
    val name: String,
    val className: String,
    val currentBorrowedBooks: MutableList<Book> = mutableListOf()
)