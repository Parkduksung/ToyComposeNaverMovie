package com.example.toycomposenavermovie.presenter.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toycomposenavermovie.common.Resource
import com.example.toycomposenavermovie.domain.model.NaverMovie
import com.example.toycomposenavermovie.domain.usecase.bookmark.GetAllBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAllBookmarkUseCase: GetAllBookmarkUseCase
) : ViewModel() {


    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> get() = _state

    init {
        getMovie(savedStateHandle.get<NaverMovie>("movie"))
    }

    private fun getMovie(movie: NaverMovie?) {
        viewModelScope.launch {

            _state.value = MovieDetailState(isLoading = true)
            delay(1000L)

            when (movie) {
                null -> {
                    _state.value = MovieDetailState(error = "선택한 영화의 정보를 가져올 수 없습니다.")
                }
                else -> {
                    getAllBookmarkUseCase().onEach { bookmarkResult ->
                        when (bookmarkResult) {
                            is Resource.Success -> {
                                val bookmarkList = bookmarkResult.data?.map { it.toNaverMovie() }

                                if(bookmarkList!!.contains(movie.copy(isBookmark = false))){
                                    _state.value = MovieDetailState(movie = movie.copy(isBookmark = true))
                                }else{
                                    _state.value = MovieDetailState(movie = movie.copy(isBookmark = false))
                                }
                            }
                        }
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

}