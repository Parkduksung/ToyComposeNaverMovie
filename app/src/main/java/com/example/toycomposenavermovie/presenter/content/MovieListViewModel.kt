package com.example.toycomposenavermovie.presenter.content

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toycomposenavermovie.common.Resource
import com.example.toycomposenavermovie.domain.use_case.get_naver_movie.GetNaverMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getNaverMovieUseCase: GetNaverMovieUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MovieListState())
    val state: State<MovieListState> get() = _state


    fun getMovies(keyword: String) {

        getNaverMovieUseCase(keyword).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MovieListState(movies = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value =
                        MovieListState(error = result.message ?: "예기치 못한 에러가 발생하였습니다.")
                }

                is Resource.Loading -> {
                    _state.value = MovieListState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)
    }

}