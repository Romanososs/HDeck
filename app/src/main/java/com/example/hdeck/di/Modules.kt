package com.example.hdeck.di

import android.util.Log
import com.example.hdeck.data_source.RetrofitDataSource
import com.example.hdeck.data_source.RetrofitDataSourceImpl
import com.example.hdeck.data_source.StoreDataSource
import com.example.hdeck.data_source.StoreDataSourceImpl
import com.example.hdeck.navigation.Navigator
import com.example.hdeck.navigation.NavigatorImpl
import com.example.hdeck.repository.AuthRepository
import com.example.hdeck.repository.AuthRepositoryImpl
import com.example.hdeck.repository.MetadataRepository
import com.example.hdeck.repository.MetadataRepositoryImpl
import com.example.hdeck.ui.MainViewModel
import com.example.hdeck.ui.MainViewModelImpl
import com.example.hdeck.ui.deck_list.DeckListViewModel
import com.example.hdeck.ui.deck_list.DeckListViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppProvideModule {
    @Singleton
    @Provides
    fun provideMetadataRepository(
        dataSource: RetrofitDataSource,
        authRepo: AuthRepository
    ): MetadataRepository = MetadataRepositoryImpl(dataSource, authRepo)

    @Singleton
    @Provides
    fun provideAuthRepository(
        store: StoreDataSource,
        dataSource: RetrofitDataSource
    ): AuthRepository = AuthRepositoryImpl(store, dataSource)

    @Singleton
    @Provides
    fun provideRetrofitDataSource(
    ): RetrofitDataSource = RetrofitDataSourceImpl(Dispatchers.IO)

    @Singleton
    @Provides
    fun provideStoreDataSource(
    ): StoreDataSource = StoreDataSourceImpl()

}

@Module
@InstallIn(ActivityComponent::class)
abstract class ActivityProvideModule {
    @Binds
    abstract fun bindNavigatorImpl(
        impl: NavigatorImpl
    ): Navigator
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelProvideModule {
    @Binds
    abstract fun bindDeckListViewModelImpl(
        impl: DeckListViewModelImpl
    ): DeckListViewModel

    @Binds
    abstract fun bindMainViewModelImpl(
        impl: MainViewModelImpl
    ): MainViewModel

//    @Binds
//    abstract fun bindNewsViewModelImpl(
//        impl: NewsViewModelImpl
//    ): NewsViewModel

}