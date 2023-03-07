package com.bayu07750.tourismapp.core.domain.usecase

import com.bayu07750.tourismapp.core.data.Resource
import com.bayu07750.tourismapp.core.domain.model.Tourism
import com.bayu07750.tourismapp.core.domain.repository.ITourismRepository
import kotlinx.coroutines.flow.Flow

class TourismInteractor(
    private val tourismRepository: ITourismRepository
) : TourismUseCase {

    override fun getAllTourism(): Flow<Resource<List<Tourism>>> {
        return tourismRepository.getAllTourism()
    }

    override fun getFavoriteTourism(): Flow<List<Tourism>> {
        return tourismRepository.getFavoriteTourism()
    }

    override suspend fun setFavoriteTourism(tourism: Tourism, state: Boolean) {
        return tourismRepository.setFavoriteTourism(tourism, state)
    }
}