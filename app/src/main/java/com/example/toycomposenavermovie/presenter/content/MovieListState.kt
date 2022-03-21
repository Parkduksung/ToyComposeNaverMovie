package com.example.toycomposenavermovie.presenter.content

import com.example.toycomposenavermovie.domain.model.NaverMovie

data class MovieListState(
    val isLoading: Boolean = false,
    val movies: List<NaverMovie> = emptyList(),
    val error: String = ""
)