package cl.practica.certificacion.di

import android.content.Context
import cl.practica.certificacion.data.local.AppDataBase
import cl.practica.certificacion.data.local.BookDao
import cl.practica.certificacion.data.remote.BookRemoteRepository
import cl.practica.certificacion.data.remote.BookService
import cl.practica.certificacion.data.repository.BookRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://my-json-server.typicode.com/Himuravidal/anchorBooks/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideBookService(retrofit: Retrofit): BookService =
        retrofit.create(BookService::class.java)

    @Singleton
    @Provides
    fun provideBookRemoteRepository(bookService: BookService) =
        BookRemoteRepository(bookService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDataBase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideBookDao(db: AppDataBase) = db.bookDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: BookRemoteRepository,
        localDataSource: BookDao
    ) = BookRepository(remoteDataSource, localDataSource)
}