package com.example.toycomposenavermovie.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.toycomposenavermovie.domain.model.NaverMovie

@Entity(tableName = "bookmark_table")
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "actor") var actor: String? = "",
    @ColumnInfo(name = "director") var director: String? = "",
    @ColumnInfo(name = "image") var image: String? = "",
    @ColumnInfo(name = "link") var link: String? = "",
    @ColumnInfo(name = "pubDate") var pubDate: String? = "",
    @ColumnInfo(name = "subtitle") var subtitle: String? = "",
    @ColumnInfo(name = "title") var title: String? = "",
    @ColumnInfo(name = "userRating") var userRating: String? = ""
) {
    fun toNaverMovie(): NaverMovie =
        NaverMovie(
            actor = actor.orEmpty(),
            director = director.orEmpty(),
            image = image.orEmpty(),
            link = link.orEmpty(),
            pubDate = pubDate.orEmpty(),
            subtitle = subtitle.orEmpty(),
            title = title.orEmpty(),
            userRating = userRating.orEmpty()
        )
}