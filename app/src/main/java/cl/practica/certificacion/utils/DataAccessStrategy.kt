package cl.practica.certificacion.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

fun <T, A> performGetOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        val responceStatus = networkCall.invoke()
        if (responceStatus.status == Resource.Status.SUCCESS) {
            saveCallResult(responceStatus.data!!)
        } else if (responceStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responceStatus.message!!))
            emitSource(source)
        }
    }