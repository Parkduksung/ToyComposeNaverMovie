package com.example.toycomposenavermovie.presenter.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.toycomposenavermovie.presenter.detail.component.WebView
import com.example.toycomposenavermovie.presenter.list.component.NaverMovieListItem

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.movie != null) {
            Column {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    NaverMovieListItem(naverMovie = state.movie!!, onItemClick = { _, _ -> })
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(8f)
                ) {
                    WebView(url = state.movie!!.link)
                }
            }
        } else {
            if (!state.isLoading) {
                Text(text = "사이트 접속을 실패하였습니다.", textAlign = TextAlign.Center)
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .semantics { testTag = "progress" })
        }
    }
}

