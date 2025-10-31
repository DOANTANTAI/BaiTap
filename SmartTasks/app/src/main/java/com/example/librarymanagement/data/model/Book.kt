package com.example.librarymanagement.data.model
data class Book(
    val bookId: String,
    val title: String,
    val author: String,
    var isAvailable: Boolean = true,
    var borrowerId: String? = null
)