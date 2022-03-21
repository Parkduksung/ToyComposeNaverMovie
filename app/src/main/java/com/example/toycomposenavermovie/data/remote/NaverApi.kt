package com.example.toycomposenavermovie.data.remote

import com.example.toycomposenavermovie.common.Constants.CLIENT_ID
import com.example.toycomposenavermovie.common.Constants.SECRET_KEY
import com.example.toycomposenavermovie.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.util.*

interface NaverApi {

    @GET("v1/search/movie.json")
    suspend fun getMovieList(
        @Header("X-Naver-Client-Id") clientId: String = CLIENT_ID,
        @Header("X-Naver-Client-Secret") secretKey: String = SECRET_KEY,
        @Query("query") keyWord: String,
        @Query("display") display: Int = INIT_DISPLAY,
        @Query("yearfrom") searchRangeYearStart: Int = START_YEAR,
        @Query("yearto") searchRangeYearEnd: Int = END_YEAR
    ): MovieResponse

    companion object {
        private const val INIT_DISPLAY = 100
        private const val START_YEAR = 2000
        private var END_YEAR = Calendar.getInstance().get(Calendar.YEAR)
    }
}