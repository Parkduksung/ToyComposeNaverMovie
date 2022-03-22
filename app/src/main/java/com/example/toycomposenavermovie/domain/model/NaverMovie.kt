package com.example.toycomposenavermovie.domain.model

import android.os.Parcelable
import com.example.toycomposenavermovie.data.local.BookmarkEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class NaverMovie(
    val actor: String,
    val director: String,
    val image: String,
    val link: String,
    val pubDate: String,
    val subtitle: String,
    val title: String,
    val userRating: String,
    var isBookmark: Boolean = false
) : Parcelable {

    fun toBookmarkEntity(): BookmarkEntity =
        BookmarkEntity(
            actor = actor,
            director = director,
            image = image,
            link = link,
            pubDate = pubDate,
            subtitle = subtitle,
            title = title,
            userRating = userRating
        )
}


fun NaverMovie.allElementIsNotEmpty(): Boolean {
    return actor.isNotEmpty() && director.isNotEmpty() && image.isNotEmpty() && link.isNotEmpty() && pubDate.isNotEmpty() && subtitle.isNotEmpty() && title.isNotEmpty() && userRating.isNotEmpty()
}