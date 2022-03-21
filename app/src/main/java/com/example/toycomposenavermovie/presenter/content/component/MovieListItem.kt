package com.example.toycomposenavermovie.presenter.content.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import com.example.toycomposenavermovie.domain.model.NaverMovie
import com.example.toycomposenavermovie.ext.convertHtml
import com.example.toycomposenavermovie.ext.convertPersons
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun NaverMovieListItem(
    naverMovie: NaverMovie,
    onItemClick: (NaverMovie) -> Unit
) {

    var bookmarkState by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable { onItemClick(naverMovie) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.Start
    ) {

        CoilImage(
            modifier = Modifier
                .fillMaxHeight()
                .weight(2f),
            imageModel = naverMovie.image,
            shimmerParams = ShimmerParams(
                baseColor = MaterialTheme.colors.background,
                highlightColor = Color.Black,
                durationMillis = 1000,
                dropOff = 0.65f,
                tilt = 20f
            ),
            contentScale = ContentScale.Crop,
            failure = {
                Text(text = "image request failed.")
            })

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(6.5f)
                .padding(start = 5.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = naverMovie.title.convertHtml(),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = Bold
            )
            Text(
                modifier = Modifier.weight(1f),
                text = "감독 : ${naverMovie.director.convertPersons()}",

                style = MaterialTheme.typography.subtitle2
            )
            Text(
                modifier = Modifier.weight(1f),
                text = "출연 : ${naverMovie.actor.convertPersons()}",
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                modifier = Modifier.weight(1f),
                text = "평점 : ${naverMovie.userRating}",
                style = MaterialTheme.typography.subtitle2
            )
        }

        Icon(
            modifier = Modifier
                .weight(1.5f)
                .padding(5.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) {
                    bookmarkState = !bookmarkState
                    onItemClick(naverMovie)
                },
            imageVector = Icons.Filled.Star,
            contentDescription = "bookmark",
            tint = if (bookmarkState) Color.Yellow else Color.LightGray
        )
    }
}