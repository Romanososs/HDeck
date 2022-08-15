package com.example.hdeck.di

import android.content.Context
import com.example.hdeck.navigation.Navigator
import com.example.hdeck.navigation.NavigatorImpl
import com.example.hdeck.repository.MetadataRepository
import com.example.hdeck.repository.MetadataRepositoryImpl
import com.example.hdeck.auth.AuthService
import com.example.hdeck.auth.AuthServiceImpl
import com.example.hdeck.data_source.*
import com.example.hdeck.repository.CardRepositoryImpl
import com.example.hdeck.ui.deck_list.DeckListViewModel
import com.example.hdeck.ui.deck_list.DeckListViewModelImpl
import com.example.hdeck.ui.main.MainViewModel
import com.example.hdeck.ui.main.MainViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
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
        authRepo: AuthService
    ): MetadataRepository = MetadataRepositoryImpl(dataSource, authRepo)

    @Singleton
    @Provides
    fun provideCardRepository(
        factory: CardsPagingSource.Factory
    ): CardRepositoryImpl = CardRepositoryImpl(factory)

    @Singleton
    @Provides
    fun provideAuthService(
        store: StoreDataSource,
        dataSource: AuthDataSource
    ): AuthService = AuthServiceImpl(store, dataSource)

    @Singleton
    @Provides
    fun provideRetrofitDataSource(
    ): RetrofitDataSource = RetrofitDataSourceImpl(Dispatchers.IO)

    @Singleton
    @Provides
    fun provideAuthDataSource(
    ): AuthDataSource = AuthDataSourceImpl(Dispatchers.IO)

    @Singleton
    @Provides
    fun provideStoreDataSource(
        @ApplicationContext context: Context
    ): StoreDataSource = StoreDataSourceImpl(context)

    @Singleton
    @Provides
    fun provideNavigator(
    ): Navigator = NavigatorImpl()
}

//@Module
//@InstallIn(ActivityComponent::class)
//abstract class ActivityProvideModule {
//    @Binds
//    abstract fun bindNavigatorImpl(
//        impl: NavigatorImpl
//    ): Navigator
//}

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