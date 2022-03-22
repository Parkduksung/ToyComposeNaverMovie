package com.example.toycomposenavermovie.presenter.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toycomposenavermovie.common.Resource
import com.example.toycomposenavermovie.domain.usecase.bookmark.GetAllBookmarkUseCase
import com.example.toycomposenavermovie.domain.usecase.naver.GetNaverMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getNaverMovieUseCase: GetNaverMovieUseCase,
    private val getAllBookmarkUseCase: GetAllBookmarkUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MovieListState())
    val state: State<MovieListState> get() = _state


    fun getMovies(keyword: String) {

        getNaverMovieUseCase(keyword).onEach { movieResult ->
            when (movieResult) {
                is Resource.Success -> {
                    getAllBookmarkUseCase().onEach { bookmarkResult ->
                        when (bookmarkResult) {
                            is Resource.Success -> {
                                val bookmarkList = bookmarkResult.data?.map { it.toNaverMovie() }


                                val checkBookmarkList = movieResult.data?.map { movie ->
                                    if (bookmarkList!!.contains(movie)) {
                                        movie.copy(isBookmark = true)
                                    } else {
                                        movie
                                    }
                                }
                                _state.value = MovieListState(movies = checkBookmarkList ?: emptyList())
                            }
                        }
                    }.launchIn(viewModelScope)
                }

                is Resource.Error -> {
                    _state.value =
                        MovieListState(error = movieResult.message ?: "예기치 못한 에러가 발생하였습니다.")
                }

                is Resource.Loading -> {
                    _state.value = MovieListState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)
    }

}