package com.dicoding.tourismapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bayu07750.tourismapp.core.domain.usecase.TourismUseCase

class HomeViewModel(
    tourismUseCase: TourismUseCase
) : ViewModel() {

    val tourism = tourismUseCase.getAllTourism().asLiveData()

}

