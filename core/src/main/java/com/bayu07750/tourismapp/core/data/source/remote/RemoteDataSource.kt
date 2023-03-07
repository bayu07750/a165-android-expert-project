package com.bayu07750.tourismapp.core.data.source.remote

import android.util.Log
import com.bayu07750.tourismapp.core.data.source.remote.network.ApiResponse
import com.bayu07750.tourismapp.core.data.source.remote.network.ApiService
import com.bayu07750.tourismapp.core.data.source.remote.response.TourismResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(
    private val apiService: ApiService,
) {
    fun getAllTourism(): Flow<ApiResponse<List<TourismResponse>>> = flow<ApiResponse<List<TourismResponse>>> {
        val response = apiService.getList()
        val places = response.places
        if (places.isEmpty()) {
            emit(ApiResponse.Empty)
            return@flow
        }

        emit(ApiResponse.Success(places))
    }.catch {
        emit(ApiResponse.Error(it.message.orEmpty()))
        Log.e("RemoteDataSource", it.toString())
    }.flowOn(Dispatchers.IO)
}

