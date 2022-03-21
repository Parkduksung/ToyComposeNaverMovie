package com.example.toycomposenavermovie.domain.repository

import com.example.toycomposenavermovie.data.remote.response.MovieResponse

interface NaverRepository {

    suspend fun getNaverMovies(keyword: String): MovieResponse
}