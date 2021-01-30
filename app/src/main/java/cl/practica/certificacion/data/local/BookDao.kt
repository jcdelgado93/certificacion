package cl.practica.certificacion.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.practica.certificacion.data.entities.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM books")
    fun getAllBooks() : LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE id = :id")
    fun getBookDetails(id: Int): LiveData<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<Book>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)
}