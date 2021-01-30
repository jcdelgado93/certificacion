package cl.practica.certificacion.data.remote

import cl.practica.certificacion.data.entities.Book
import cl.practica.certificacion.data.entities.BookList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookService {
    @GET("bookDetail")
    suspend fun getAllBooks(): Response<BookList>

    @GET("bookDetail/{id}")
    suspend fun getBookDetails(@Path("id") id: Int): Response<Book>
}