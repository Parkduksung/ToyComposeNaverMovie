package com.example.toycomposenavermovie.data.repository

import com.example.toycomposenavermovie.data.remote.NaverApi
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import utils.MockUtil

class NaverRepositoryImplTest {

    private lateinit var naverRepositoryImpl: NaverRepositoryImpl
    private val naverApi: NaverApi = mock()

    @Before
    fun setUp(){
        naverRepositoryImpl = NaverRepositoryImpl(naverApi)
    }

    @Test
    fun getNaverMoviesTest() = runBlocking {

        //given
        whenever(naverApi.getMovieList(keyWord = "국가")).thenReturn(MockUtil.mockNaverReponse())

        //when, then
        assertEquals(naverRepositoryImpl.getNaverMovies(keyword = "국가") , MockUtil.mockNaverReponse())

    }

}