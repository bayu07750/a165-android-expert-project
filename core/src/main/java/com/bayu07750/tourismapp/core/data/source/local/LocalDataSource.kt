package com.bayu07750.tourismapp.core.data.source.local

import com.bayu07750.tourismapp.core.data.source.local.entity.TourismEntity
import com.bayu07750.tourismapp.core.data.source.local.room.TourismDao

class LocalDataSource(private val tourismDao: TourismDao) {

    fun getAllTourism() = tourismDao.getAllTourism()

    fun getFavoriteTourism() = tourismDao.getFavoriteTourism()

    suspend fun insertTourism(tourismList: List<TourismEntity>) = tourismDao.insertTourism(tourismList)

    suspend fun setFavoriteTourism(tourism: TourismEntity, newState: Boolean) {
        tourism.isFavorite = newState
        tourismDao.updateFavoriteTourism(tourism)
    }
}