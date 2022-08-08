package com.example.hdeck.repository

import com.example.hdeck.data_source.RetrofitDataSource
import com.example.hdeck.model.HeroClass
import com.example.hdeck.state.BaseState
import javax.inject.Inject

interface MetadataRepository {
    suspend fun getCardSetList()
    suspend fun getCardRarityList()
    suspend fun getHeroClassList():List<HeroClass>
}

class MetadataRepositoryImpl @Inject constructor(
    private val dataSource: RetrofitDataSource,
    private val authRepo: AuthRepository
): MetadataRepository, BaseRepository {
    override suspend fun getCardSetList(){

    }
    override suspend fun getCardRarityList(){

    }
    override suspend fun getHeroClassList(): List<HeroClass> {
        return dataSource.getHeroClassList("en_US", authRepo.getToken())
    }
}