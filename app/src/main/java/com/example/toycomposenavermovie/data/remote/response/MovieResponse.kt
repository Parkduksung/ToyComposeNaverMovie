package com.example.toycomposenavermovie.data.remote.response


import com.example.toycomposenavermovie.domain.model.NaverMovie
import com.google.gson.annotations.SerializedName


data class MovieResponse(
    @SerializedName("display") val display: Int,
    @SerializedName("items") val movieList: List<Movie>,
    @SerializedName("lastBuildDate") val lastBuildDate: String,
    @SerializedName("start") val start: Int,
    @SerializedName("total") val total: Int
)

data class Movie(
    @SerializedName("actor") val actor: String,
    @SerializedName("director") val director: String,
    @SerializedName("image") val image: String,
    @SerializedName("link") val link: String,
    @SerializedName("pubDate") val pubDate: String,
    @SerializedName("subtitle") val subtitle: String,
    @SerializedName("title") val title: String,
    @SerializedName("userRating") val userRating: String
) {
    fun toNaverMovie(): NaverMovie =
        NaverMovie(
            actor, director, image, link, pubDate, subtitle, title, userRating
        )
}
