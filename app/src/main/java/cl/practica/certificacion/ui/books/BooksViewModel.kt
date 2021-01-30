package cl.practica.certificacion.ui.books

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cl.practica.certificacion.data.entities.Book
import cl.practica.certificacion.data.repository.BookRepository
import cl.practica.certificacion.utils.Resource

class BooksViewModel @ViewModelInject constructor(
    repository: BookRepository
) : ViewModel() {
    val books: LiveData<Resource<List<Book>>> = repository.getBooks()
}