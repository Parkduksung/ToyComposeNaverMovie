package com.example.toycomposenavermovie.presenter.detail

import com.example.toycomposenavermovie.domain.model.NaverMovie

class MovieDetailState(
    val isLoading: Boolean = false,
    var movie: NaverMovie? = null,
    val error: String = ""
)
