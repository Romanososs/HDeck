package com.example.hdeck.di

import android.content.Context
import com.example.hdeck.BuildConfig
import com.example.hdeck.auth.AuthService
import com.example.hdeck.auth.AuthServiceImpl
import com.example.hdeck.data_source.*
import com.example.hdeck.localization.LocaleService
import com.example.hdeck.localization.LocaleServiceImpl
import com.example.hdeck.navigation.Navigator
import com.example.hdeck.navigation.NavigatorImpl
import com.example.hdeck.repository.CardRepositoryImpl
import com.example.hdeck.repository.MetadataRepository
import com.example.hdeck.repository.MetadataRepositoryImpl
import com.example.hdeck.ui.card_info.CardInfoViewModel
import com.example.hdeck.ui.card_info.CardInfoViewModelImpl
import com.example.hdeck.ui.card_list.CardListViewModel
import com.example.hdeck.ui.card_list.CardListViewModelImpl
import com.example.hdeck.ui.main.MainViewModel
import com.example.hdeck.ui.main.MainViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseRetrofit

@Module
@InstallIn(SingletonComponent::class)
class AppProvideModule {
    @Singleton
    @Provides
    fun provideMetadataRepository(
        dataSource: RetrofitDataSource,
        localeService: LocaleService,
        authService: AuthService
    ): MetadataRepository = MetadataRepositoryImpl(dataSource, localeService, authService)

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
    fun provideLocaleService(
        store: StoreDataSource,
    ): LocaleService = LocaleServiceImpl(store)

    @Singleton
    @Provides
    fun provideRetrofitDataSource(
        @BaseRetrofit retrofit: RetrofitApi
    ): RetrofitDataSource = RetrofitDataSourceImpl(Dispatchers.IO, retrofit)

    @Singleton
    @Provides
    fun provideAuthDataSource(
        @AuthRetrofit retrofit: RetrofitApi
    ): AuthDataSource = AuthDataSourceImpl(Dispatchers.IO, retrofit)

    @Singleton
    @Provides
    fun provideStoreDataSource(
        @ApplicationContext context: Context
    ): StoreDataSource = StoreDataSourceImpl(context)

    @Singleton
    @Provides
    fun provideNavigator(
    ): Navigator = NavigatorImpl()

    @Singleton
    @BaseRetrofit
    @Provides
    fun provideRetrofitApi(
        client: OkHttpClient
    ): RetrofitApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)

    @Singleton
    @AuthRetrofit
    @Provides
    fun provideAuthRetrofitApi(
        client: OkHttpClient
    ): RetrofitApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.AUTH_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)

    @Singleton
    @Provides
    fun provideOkHttpClient(
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()

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
    abstract fun bindCardListViewModelImpl(
        impl: CardListViewModelImpl
    ): CardListViewModel

    @Binds
    abstract fun bindMainViewModelImpl(
        impl: MainViewModelImpl
    ): MainViewModel

    @Binds
    abstract fun bindCardInfoViewModelImpl(
        impl: CardInfoViewModelImpl
    ): CardInfoViewModel
}