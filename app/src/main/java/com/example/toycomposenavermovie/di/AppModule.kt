package com.example.toycomposenavermovie.di


import android.content.Context
import androidx.room.Room
import com.example.toycomposenavermovie.common.Constants
import com.example.toycomposenavermovie.data.local.BookmarkDao
import com.example.toycomposenavermovie.data.local.BookmarkDatabase
import com.example.toycomposenavermovie.data.remote.NaverApi
import com.example.toycomposenavermovie.data.repository.BookmarkRepositoryImpl
import com.example.toycomposenavermovie.data.repository.NaverRepositoryImpl
import com.example.toycomposenavermovie.domain.repository.BookmarkRepository
import com.example.toycomposenavermovie.domain.repository.NaverRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideBookmarkDao(bookmarkDatabase: BookmarkDatabase): BookmarkDao {
        return bookmarkDatabase.bookmarkDao()
    }

    @Provides
    @Singleton
    fun provideBookmarkDatabase(@ApplicationContext appContext: Context): BookmarkDatabase {
        return Room.databaseBuilder(
            appContext,
            BookmarkDatabase::class.java,
            "bookmark_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNaverRepository(api: NaverApi): NaverRepository {
        return NaverRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideBookmarkRepository(bookmarkDao: BookmarkDao): BookmarkRepository {
        return BookmarkRepositoryImpl(bookmarkDao)
    }
}