package com.bayu07750.tourismapp.core.domain.repository

import com.bayu07750.tourismapp.core.data.Resource
import com.bayu07750.tourismapp.core.domain.model.Tourism
import kotlinx.coroutines.flow.Flow

interface ITourismRepository {
    fun getAllTourism(): Flow<Resource<List<Tourism>>>
    fun getFavoriteTourism(): Flow<List<Tourism>>
    suspend fun setFavoriteTourism(tourism: Tourism, state: Boolean)
}