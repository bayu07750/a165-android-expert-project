package com.bayu07750.tourismapp.core.data

import com.bayu07750.tourismapp.core.data.source.local.LocalDataSource
import com.bayu07750.tourismapp.core.data.source.remote.RemoteDataSource
import com.bayu07750.tourismapp.core.data.source.remote.network.ApiResponse
import com.bayu07750.tourismapp.core.data.source.remote.response.TourismResponse
import com.bayu07750.tourismapp.core.domain.model.Tourism
import com.bayu07750.tourismapp.core.domain.repository.ITourismRepository
import com.bayu07750.tourismapp.core.utils.AppExecutors
import com.bayu07750.tourismapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TourismRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITourismRepository {

    override fun getAllTourism(): Flow<Resource<List<Tourism>>> =
        object : NetworkBoundResource<List<Tourism>, List<TourismResponse>>() {

            override fun loadFromDB(): Flow<List<Tourism>> {
                return localDataSource.getAllTourism().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TourismResponse>>> {
                return remoteDataSource.getAllTourism()
            }

            override suspend fun saveCallResult(data: List<TourismResponse>) {
                localDataSource.insertTourism(DataMapper.mapResponsesToEntities(data))
            }

            override fun shouldFetch(data: List<Tourism>?): Boolean {
                return data == null || data.isEmpty()
            }
        }.asFlow()

    override fun getFavoriteTourism(): Flow<List<Tourism>> {
        return localDataSource.getFavoriteTourism().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun setFavoriteTourism(tourism: Tourism, state: Boolean) {
        localDataSource.setFavoriteTourism(DataMapper.mapDomainToEntity(tourism), state)
    }
}

