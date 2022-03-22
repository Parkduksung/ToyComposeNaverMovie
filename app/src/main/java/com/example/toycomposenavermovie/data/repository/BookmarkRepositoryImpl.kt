package com.example.toycomposenavermovie.data.repository

import com.example.toycomposenavermovie.data.local.BookmarkDao
import com.example.toycomposenavermovie.data.local.BookmarkEntity
import com.example.toycomposenavermovie.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(private val bookmarkDao: BookmarkDao) :
    BookmarkRepository {

    override suspend fun registerBookmark(entity: BookmarkEntity): Boolean {
        return bookmarkDao.registerBookmarkEntity(entity = entity) > 0
    }

    override suspend fun deleteBookmark(entity: BookmarkEntity): Boolean {
        return bookmarkDao.deleteBookmarkEntity(
            title = entity.title,
            image = entity.image,
            link = entity.link
        ) >= 1
    }

    override suspend fun getAll(): Flow<List<BookmarkEntity>> {
        return bookmarkDao.getAll()
    }
}