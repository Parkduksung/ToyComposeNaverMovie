package com.example.toycomposenavermovie.presenter.bookmark

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.toycomposenavermovie.common.ItemClickType
import com.example.toycomposenavermovie.domain.model.allElementIsNotEmpty
import com.example.toycomposenavermovie.presenter.Screen
import com.example.toycomposenavermovie.presenter.list.component.NaverMovieListItem
import com.google.gson.Gson

@Composable
fun BookmarkScreen(
    navController : NavController,
    viewModel: BookmarkViewModel = hiltViewModel()
) {

    val movieState = viewModel.state.value


    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .semantics { testTag = "lazyNaverMovieListColumn" }) {
            items(movieState.movies) { movie ->
                if (movie.allElementIsNotEmpty()) {
                    NaverMovieListItem(
                        naverMovie = movie,
                        onItemClick = { movie, type ->
                            when (type) {
                                is ItemClickType.LoadUrl -> {
                                    val toJson = Uri.encode(Gson().toJson(movie))
                                    navController.navigate(Screen.NaverDetailScreen.route + "/$toJson")
                                }

                                is ItemClickType.Bookmark -> {

                                }
                            }
                        }
                    )
                }
            }
        }
    }
}