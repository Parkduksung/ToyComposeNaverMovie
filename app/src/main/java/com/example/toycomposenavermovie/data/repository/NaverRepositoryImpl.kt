package com.example.toycomposenavermovie.data.repository

import com.example.toycomposenavermovie.data.remote.NaverApi
import com.example.toycomposenavermovie.data.remote.response.MovieResponse
import com.example.toycomposenavermovie.domain.repository.NaverRepository
import javax.inject.Inject

class NaverRepositoryImpl @Inject constructor(private val naverApi: NaverApi) : NaverRepository {
    override suspend fun getNaverMovies(keyword: String): MovieResponse {
        return naverApi.getMovieList(keyWord = keyword)
    }
}