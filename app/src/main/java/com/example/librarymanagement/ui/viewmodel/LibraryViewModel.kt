package com.example.librarymanagement.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarymanagement.data.model.Book
import com.example.librarymanagement.data.model.Student
import com.example.librarymanagement.data.repository.LibraryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LibraryViewModel : ViewModel() {
    private val repository = LibraryRepository()
    private val _currentStudent = MutableStateFlow<Student?>(null)
    val currentStudent: StateFlow<Student?> = _currentStudent.asStateFlow()
    private val _allBooks = MutableStateFlow(repository.getBooks())
    val allBooks: StateFlow<List<Book>> = _allBooks.asStateFlow()
    var selectedBooksForBorrow = mutableStateOf(listOf<Book>())
        private set

    init {
        _currentStudent.value = repository.getStudents().firstOrNull()
    }
    fun getStudentList(): List<Student> = repository.getStudents()
    fun selectStudent(studentId: String) {
        _currentStudent.value = repository.findStudentById(studentId)
        selectedBooksForBorrow.value = emptyList()
    }
    fun toggleBookSelection(book: Book, isSelected: Boolean) {
        val currentSelection = selectedBooksForBorrow.value.toMutableList()
        if (isSelected) {
            currentSelection.add(book)
        } else {
            currentSelection.removeIf { it.bookId == book.bookId }
        }
        selectedBooksForBorrow.value = currentSelection
    }
    fun attemptBorrow(): Boolean {
        val student = _currentStudent.value ?: return false
        val booksToBorrow = selectedBooksForBorrow.value
        if (booksToBorrow.isEmpty()) return false
        val success = repository.borrowBooks(student.studentId, booksToBorrow)
        if (success) {
            _allBooks.value = repository.getBooks()
            _currentStudent.value = repository.findStudentById(student.studentId)
            selectedBooksForBorrow.value = emptyList()
        }
        return success
    }
    fun addNewStudent(name: String, className: String) {
        repository.addStudent(name, className)
    }
    fun addNewBook(title: String, author: String) {
        repository.addBook(title, author)
        _allBooks.value = repository.getBooks()
    }
}