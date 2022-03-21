package com.example.toycomposenavermovie.domain.model

data class NaverMovie(
    val actor: String,
    val director: String,
    val image: String,
    val link: String,
    val pubDate: String,
    val subtitle: String,
    val title: String,
    val userRating: String
)


fun NaverMovie.allElementIsNotEmpty(): Boolean {
    return actor.isNotEmpty() && director.isNotEmpty() && image.isNotEmpty() && link.isNotEmpty() && pubDate.isNotEmpty() && subtitle.isNotEmpty() && title.isNotEmpty() && userRating.isNotEmpty()
}