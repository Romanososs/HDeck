package com.example.hdeck.repository

import com.example.hdeck.data_source.RetrofitDataSource
import com.example.hdeck.model.CardRarity
import com.example.hdeck.model.CardSet
import com.example.hdeck.model.HeroClass
import com.example.hdeck.auth.AuthService
import javax.inject.Inject

interface MetadataRepository {
    suspend fun getCardSetList(): List<CardSet>
    suspend fun getCardRarityList(): List<CardRarity>
    suspend fun getHeroClassList(): List<HeroClass>
}

class MetadataRepositoryImpl @Inject constructor(
    private val dataSource: RetrofitDataSource,
    private val authService: AuthService
) : MetadataRepository, BaseRepository {

    override suspend fun getCardSetList(): List<CardSet> {
        return dataSource.getCardSetList("en_US", authService.getToken())
    }

    override suspend fun getCardRarityList(): List<CardRarity> {
        return dataSource.getCardRarityList("en_US", authService.getToken())
    }

    override suspend fun getHeroClassList(): List<HeroClass> {
        return dataSource.getHeroClassList("en_US", authService.getToken())
    }
}