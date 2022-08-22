package com.example.hdeck.service

import com.example.hdeck.data_source.RetrofitDataSource
import com.example.hdeck.auth.AuthService
import com.example.hdeck.localization.LocaleService
import com.example.hdeck.model.Language
import com.example.hdeck.model.metadata.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

interface MetadataService {
    suspend fun getCardSetList(): List<CardSet>
    suspend fun getCardRarityList(): List<CardRarity>
    suspend fun getHeroClassList(): List<HeroClass>
    suspend fun getHeroClass(id: Int): HeroClass
    suspend fun getCardSet(id: Int): CardSet
    suspend fun getType(id: Int): CardType
    suspend fun getRarity(id: Int): CardRarity
}

class MetadataServiceImpl @Inject constructor(
    private val dataSource: RetrofitDataSource,
    private val localeService: LocaleService,
    private val authService: AuthService,
) : MetadataService, BaseRepository {

    private var data: Metadata? = null
    private var currentLocale: Language? = null
    private val mutex = Mutex()


    private suspend fun getMetadata(): Metadata {
        mutex.withLock {
            val lang = localeService.getLanguage()
            return if (data != null && lang == currentLocale) data!!
            else dataSource.getMetadata(localeService.getApiLocale(), authService.getToken())
                .also {
                    data = it
                    currentLocale = lang
                }
        }
    }

    override suspend fun getCardSetList(): List<CardSet> {
        return getMetadata().sets
    }

    override suspend fun getCardRarityList(): List<CardRarity> {
        return getMetadata().rarities
    }

    override suspend fun getHeroClassList(): List<HeroClass> {
        return getMetadata().classes
    }

    override suspend fun getHeroClass(id: Int): HeroClass {
        return getMetadata().classes.first { it.id == id }
    }

    override suspend fun getCardSet(id: Int): CardSet {
        return getMetadata().sets.first { it.id == id }
    }

    override suspend fun getType(id: Int): CardType {
        return getMetadata().types.first { it.id == id }
    }

    override suspend fun getRarity(id: Int): CardRarity {
        return getMetadata().rarities.first { it.id == id }
    }
}