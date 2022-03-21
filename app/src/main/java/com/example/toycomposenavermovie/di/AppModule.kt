package com.example.toycomposenavermovie.di


import com.example.toycomposenavermovie.common.Constants
import com.example.toycomposenavermovie.data.remote.NaverApi
import com.example.toycomposenavermovie.data.repository.NaverRepositoryImpl
import com.example.toycomposenavermovie.domain.repository.NaverRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNaverApi(): NaverApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_NAVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NaverApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNaverRepository(api: NaverApi): NaverRepository {
        return NaverRepositoryImpl(api)
    }
}