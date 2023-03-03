package com.dicoding.tourismapp.core.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.tourismapp.core.data.source.remote.network.ApiResponse
import com.dicoding.tourismapp.core.data.source.remote.network.ApiService
import com.dicoding.tourismapp.core.data.source.remote.response.ListTourismResponse
import com.dicoding.tourismapp.core.data.source.remote.response.TourismResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(
    private val apiService: ApiService,
) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiService)
            }
    }

    fun getAllTourism(): LiveData<ApiResponse<List<TourismResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<TourismResponse>>>()

        val client = apiService.getList()
        client.enqueue(object : Callback<ListTourismResponse> {

            override fun onResponse(call: Call<ListTourismResponse>, response: Response<ListTourismResponse>) {
                val places = response.body()?.places ?: emptyList()
                resultData.postValue(
                    if (places.isNotEmpty()) ApiResponse.Success(places) else ApiResponse.Empty
                )
            }

            override fun onFailure(call: Call<ListTourismResponse>, t: Throwable) {
                resultData.postValue(ApiResponse.Error(t.message.orEmpty()))
            }
        })

        return resultData
    }
}

