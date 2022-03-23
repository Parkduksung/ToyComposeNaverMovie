package com.example.toycomposenavermovie.domain.usecase.bookmark

import com.example.toycomposenavermovie.common.Resource
import com.example.toycomposenavermovie.domain.repository.BookmarkRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import utils.MockUtil

class GetDeleteBookmarkUseCaseTest {

    private lateinit var getDeleteBookmarkUseCase: GetDeleteBookmarkUseCase
    private val bookmarkRepository: BookmarkRepository = mock()


    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        getDeleteBookmarkUseCase = GetDeleteBookmarkUseCase(bookmarkRepository)
    }

    @Test
    fun invokeSuccessTest() = runBlocking {

        val mockEntity = MockUtil.mockNaverMovie().toBookmarkEntity()
        //given
        whenever(bookmarkRepository.deleteBookmark(mockEntity)).thenReturn(true)

        //when
        getDeleteBookmarkUseCase(mockEntity).collect { resource ->
            when (resource) {

                //then
                is Resource.Loading -> {
                    Assert.assertEquals(resource.message, null)
                    Assert.assertEquals(resource.data, null)
                }

                is Resource.Success -> {
                    Assert.assertEquals(resource.message, null)
                    Assert.assertEquals(
                        resource.data,
                        true
                    )
                }
            }
        }
    }


    @Test
    fun invokeFailTest() = runBlocking {
        val mockEntity = MockUtil.mockNaverMovie().toBookmarkEntity()

        //given
        whenever(bookmarkRepository.registerBookmark(mockEntity)).then { (Exception("Couldn't reach server. Check your internet connection.")) }

        //when
        getDeleteBookmarkUseCase(mockEntity).collect { resource ->
            when (resource) {

                //then
                is Resource.Loading -> {
                    Assert.assertEquals(resource.message, null)
                    Assert.assertEquals(resource.data, null)
                }

                is Resource.Error -> {
                    Assert.assertEquals(
                        resource.message,
                        "Couldn't reach server. Check your internet connection."
                    )
                    Assert.assertEquals(resource.data, null)
                }
            }
        }
    }
}