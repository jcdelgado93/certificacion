package cl.practica.certificacion.data.repository

import cl.practica.certificacion.data.local.BookDao
import cl.practica.certificacion.data.remote.BookRemoteRepository
import cl.practica.certificacion.utils.performGetOperation
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val remoteDataSource: BookRemoteRepository,
    private val localDataSource: BookDao
) {

    fun getBook(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getBookDetails(id) },
        networkCall = { remoteDataSource.getBookDetails(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getBooks() = performGetOperation(
        databaseQuery = { localDataSource.getAllBooks() },
        networkCall = { remoteDataSource.getAllBooks() },
        saveCallResult = { localDataSource.insertAll(it) }
    )
}