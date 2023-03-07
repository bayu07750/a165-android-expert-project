package com.bayu07750.tourismapp.core.data.source.remote.network

import com.bayu07750.tourismapp.core.data.source.remote.response.ListTourismResponse
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    suspend fun getList(): ListTourismResponse
}