package com.example.toycomposenavermovie.domain.usecase.bookmark

import com.example.toycomposenavermovie.common.Resource
import com.example.toycomposenavermovie.domain.repository.BookmarkRepository
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import utils.MockUtil

class GetAllBookmarkUseCaseTest {

    private lateinit var getAllBookmarkUseCase: GetAllBookmarkUseCase
    private val bookmarkRepository: BookmarkRepository = mock()


    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        getAllBookmarkUseCase = GetAllBookmarkUseCase(bookmarkRepository)
    }

    @Test
    fun invokeSuccessTest() = runBlocking {

        val mockList = flowOf(MockUtil.mockNaverReponse().movieList.map {
            it.toNaverMovie().toBookmarkEntity()
        })


        //given
        whenever(bookmarkRepository.getAll()).thenReturn(mockList)


        //when
        getAllBookmarkUseCase().collect { resource ->
            when (resource) {

                //then
                is Resource.Loading -> {
                    Assert.assertEquals(resource.message, null)
                    Assert.assertEquals(resource.data, null)
                }

                //then
                is Resource.Success -> {
                    Assert.assertEquals(resource.message, null)
                    Assert.assertEquals(
                        resource.data,
                        mockList.first()
                    )
                }

            }
        }
    }


    @Test
    fun invokeFailTest() = runBlocking {

        //given
        bookmarkRepository.stub {
            onBlocking { getAll() } doAnswer {
                throw Exception("Couldn't reach server. Check your internet connection.")
            }
        }


        //when
        getAllBookmarkUseCase().collect { resource ->
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