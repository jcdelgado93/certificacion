package cl.practica.certificacion.data.remote

import javax.inject.Inject

class BookRemoteRepository @Inject constructor(
    private val bookService: BookService
): BaseDataSource() {

    suspend fun getAllBooks() = getBooks {
        bookService.getAllBooks()
    }

    suspend fun getBookDetails(id: Int) = getBooks {
        bookService.getBookDetails(id)
    }
}