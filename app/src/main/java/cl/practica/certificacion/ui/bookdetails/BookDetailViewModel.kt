package cl.practica.certificacion.ui.bookdetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import cl.practica.certificacion.data.entities.Book
import cl.practica.certificacion.data.repository.BookRepository
import cl.practica.certificacion.utils.Resource

class BookDetailViewModel @ViewModelInject constructor(
    private val repository: BookRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _book = _id.switchMap { id ->
        repository.getBook(id)
    }

    val book: LiveData<Resource<Book>> = _book

    fun start(id: Int) {
        _id.value = id
    }
}