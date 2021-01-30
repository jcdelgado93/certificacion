package cl.practica.certificacion.data.remote

import cl.practica.certificacion.data.entities.Book
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookService {
    @GET("bookDetail")
    suspend fun getAllBooks(): Response<List<Book>>

    @GET("bookDetail/{id}")
    suspend fun getBookDetails(@Path("id") id: Int): Response<Book>
}