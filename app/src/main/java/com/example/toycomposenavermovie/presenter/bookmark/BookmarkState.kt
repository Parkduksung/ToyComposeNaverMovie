package com.example.toycomposenavermovie.presenter.bookmark

import com.example.toycomposenavermovie.domain.model.NaverMovie

data class BookmarkState(
    val isLoading: Boolean = false,
    val movies: List<NaverMovie> = emptyList(),
    val isRegisterBookmark: Boolean = false,
    val isDeleteBookmark: Boolean = false,
    val error: String = ""
)