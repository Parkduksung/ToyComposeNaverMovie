package com.example.toycomposenavermovie.domain.use_case.get_naver_movie

import com.example.toycomposenavermovie.common.Resource
import com.example.toycomposenavermovie.domain.repository.NaverRepository
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import utils.MockUtil
import java.io.IOException


class GetNaverMovieUseCaseTest {

    private lateinit var getNaverMovieUseCase: GetNaverMovieUseCase
    private val naverRepository: NaverRepository = mock()


    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        getNaverMovieUseCase = GetNaverMovieUseCase(naverRepository)
    }

    @Test
    fun invokeSuccessTest() = runBlocking {

        //given
        whenever(naverRepository.getNaverMovies(keyword = "국가")).thenReturn(MockUtil.mockNaverReponse())

        //when
        getNaverMovieUseCase(keyword = "국가").take(getNaverMovieUseCase(keyword = "국가").count())
            .toList().forEach { resource ->
                when (resource) {

                    //then
                    is Resource.Loading -> {
                        assertEquals(resource.message, null)
                        assertEquals(resource.data, null)
                    }

                    is Resource.Success -> {
                        assertEquals(resource.message, null)
                        assertEquals(
                            MockUtil.mockNaverReponse().movieList[0].actor,
                            MockUtil.mockNaverReponse().movieList[0].toNaverMovie().actor
                        )
                        assertEquals(
                            resource.data,
                            MockUtil.mockNaverReponse().movieList.map { it.toNaverMovie() })
                    }
                }
            }
    }


    @Test
    fun invokeFailTest() = runBlocking {

        //given
        naverRepository.stub {
            onBlocking { getNaverMovies(keyword = "국가") } doAnswer {
                throw IOException("Couldn't reach server. Check your internet connection.")
            }
        }

        //when
        getNaverMovieUseCase(keyword = "국가").take(getNaverMovieUseCase(keyword = "국가").count())
            .toList().forEach { resource ->

                when (resource) {

                    //then
                    is Resource.Loading -> {
                        assertEquals(resource.message, null)
                        assertEquals(resource.data, null)
                    }

                    is Resource.Error -> {
                        assertEquals(
                            resource.message,
                            "Couldn't reach server. Check your internet connection."
                        )
                        assertEquals(resource.data, null)
                    }
                }
            }
    }
}