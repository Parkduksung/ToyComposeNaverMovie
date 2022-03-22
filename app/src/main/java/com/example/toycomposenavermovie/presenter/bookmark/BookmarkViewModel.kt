package com.example.toycomposenavermovie.presenter.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toycomposenavermovie.common.Resource
import com.example.toycomposenavermovie.domain.model.NaverMovie
import com.example.toycomposenavermovie.domain.usecase.bookmark.GetAllBookmarkUseCase
import com.example.toycomposenavermovie.domain.usecase.bookmark.GetDeleteBookmarkUseCase
import com.example.toycomposenavermovie.domain.usecase.bookmark.GetRegisterBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getRegisterBookmarkUseCase: GetRegisterBookmarkUseCase,
    private val getDeleteBookmarkUseCase: GetDeleteBookmarkUseCase,
    private val getAllBookmarkUseCase: GetAllBookmarkUseCase
) : ViewModel() {

    private val _state = mutableStateOf(BookmarkState())
    val state: State<BookmarkState> get() = _state

    init {
        getAll()
    }

    fun registerBookmark(movie: NaverMovie) {
        getRegisterBookmarkUseCase(movie.toBookmarkEntity()).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = BookmarkState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = BookmarkState(isRegisterBookmark = true)
                }

                is Resource.Error -> {
                    _state.value =
                        BookmarkState(error = result.message ?: "예기치 못한 에러가 발생하였습니다.")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteBookmark(movie: NaverMovie) {
        getDeleteBookmarkUseCase(movie.toBookmarkEntity()).onEach { result ->
            when (result) {

                is Resource.Loading -> {
                    _state.value = BookmarkState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = BookmarkState(isDeleteBookmark = true)
                }

                is Resource.Error -> {
                    _state.value =
                        BookmarkState(error = result.message ?: "예기치 못한 에러가 발생하였습니다.")
                }

            }
        }.launchIn(viewModelScope)
    }

    private fun getAll() {
        getAllBookmarkUseCase().onEach { result ->
            when (result) {

                is Resource.Loading -> {
                    _state.value = BookmarkState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = BookmarkState(movies = result.data?.map { it.toNaverMovie() }
                        ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value =
                        BookmarkState(error = result.message ?: "예기치 못한 에러가 발생하였습니다.")
                }

            }
        }.launchIn(viewModelScope)
    }
}