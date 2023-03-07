package com.bayu07750.tourismapp.core.domain.usecase

import com.bayu07750.tourismapp.core.data.Resource
import com.bayu07750.tourismapp.core.domain.model.Tourism
import kotlinx.coroutines.flow.Flow

interface TourismUseCase {
    fun getAllTourism(): Flow<Resource<List<Tourism>>>
    fun getFavoriteTourism(): Flow<List<Tourism>>
    suspend fun setFavoriteTourism(tourism: Tourism, state: Boolean)
}