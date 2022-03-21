package com.example.toycomposenavermovie.domain.use_case.get_naver_movie

import com.example.toycomposenavermovie.common.Resource
import com.example.toycomposenavermovie.domain.model.NaverMovie
import com.example.toycomposenavermovie.domain.repository.NaverRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNaverMovieUseCase @Inject constructor(
    private val naverRepository: NaverRepository
) {
    operator fun invoke(keyword: String): Flow<Resource<List<NaverMovie>>> = flow {
        try {
            emit(Resource.Loading<List<NaverMovie>>())
            val movies = naverRepository.getNaverMovies(keyword).movieList.map { it.toNaverMovie() }
            emit(Resource.Success<List<NaverMovie>>(movies))
        } catch (e: HttpException) {
            emit(Resource.Error<List<NaverMovie>>(e.localizedMessage ?: "예기지 못한 에러가 발생하였습니다."))
        } catch (e: IOException) {
            emit(Resource.Error<List<NaverMovie>>(e.localizedMessage ?: "예기지 못한 에러가 발생하였습니다."))
        }
    }
}