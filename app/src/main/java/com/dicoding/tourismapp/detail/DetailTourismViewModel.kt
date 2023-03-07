package com.dicoding.tourismapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bayu07750.tourismapp.core.domain.model.Tourism
import com.bayu07750.tourismapp.core.domain.usecase.TourismUseCase
import kotlinx.coroutines.launch

class DetailTourismViewModel(
    private val tourismUseCase: TourismUseCase
) : ViewModel() {
    fun setFavoriteTourism(tourism: Tourism, newStatus: Boolean) {
        viewModelScope.launch {
            tourismUseCase.setFavoriteTourism(tourism, newStatus)
        }
    }
}

