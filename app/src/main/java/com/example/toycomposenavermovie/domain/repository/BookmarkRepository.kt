package com.example.toycomposenavermovie.domain.repository

import com.example.toycomposenavermovie.data.local.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {

    suspend fun registerBookmark(entity: BookmarkEntity) : Boolean

    suspend fun deleteBookmark(entity: BookmarkEntity): Boolean

    suspend fun getAll(): Flow<List<BookmarkEntity>>
}