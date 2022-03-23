package com.example.toycomposenavermovie.data.repository

import com.example.toycomposenavermovie.data.local.BookmarkDao
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import utils.MockUtil.mockNaverMovie
import utils.MockUtil.mockNaverReponse

class BookmarkRepositoryImplTest {

    private lateinit var bookmarkRepositoryImpl: BookmarkRepositoryImpl
    private val bookmarkDao: BookmarkDao = mock()


    @Before
    fun setUp() {
        bookmarkRepositoryImpl = BookmarkRepositoryImpl(bookmarkDao)
    }

    @Test
    fun getAllMovieEntityTest() = runBlocking {
        //given
        val mockList = flowOf(mockNaverReponse().movieList.map {
            it.toNaverMovie().toBookmarkEntity()
        })
        whenever(bookmarkDao.getAll()).thenReturn(mockList)

        //when, then
        Assert.assertEquals(bookmarkRepositoryImpl.getAll(), mockList)

    }

    @Test
    fun registerMovieEntityTest() = runBlocking {
        //given
        val mockEntity = mockNaverMovie().toBookmarkEntity()
        whenever(bookmarkDao.registerBookmarkEntity(mockEntity)).thenReturn(1)

        //when, then
        Assert.assertEquals(bookmarkRepositoryImpl.registerBookmark(mockEntity), true)

    }

    @Test
    fun deleteMovieEntityTest() = runBlocking {
        //given
        val mockEntity = mockNaverMovie().toBookmarkEntity()
        whenever(
            bookmarkDao.deleteBookmarkEntity(
                title = mockEntity.title,
                image = mockEntity.image,
                link = mockEntity.link
            )
        ).thenReturn(1)

        //when, then
        Assert.assertEquals(bookmarkRepositoryImpl.deleteBookmark(mockEntity), true)

    }

}