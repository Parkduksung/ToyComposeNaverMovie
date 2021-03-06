package com.example.toycomposenavermovie.presenter.list

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.toycomposenavermovie.common.ItemClickType
import com.example.toycomposenavermovie.domain.model.allElementIsNotEmpty
import com.example.toycomposenavermovie.presenter.Screen
import com.example.toycomposenavermovie.presenter.list.component.NaverMovieListItem
import com.google.gson.Gson

@Composable
fun MovieListScreen(
    navController: NavController,
    movieViewModel: MovieListViewModel = hiltViewModel()
) {
    val movieState = movieViewModel.state.value

    var inputSearchState by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.weight(7f),
                    text = "????????? ?????? ??????",
                    style = MaterialTheme.typography.h6,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )
                OutlinedButton(modifier = Modifier
                    .weight(3f)
                    .align(CenterVertically),
                    onClick = { navController.navigate(Screen.BookmarkScreen.route) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "bookmark",
                        tint = Yellow
                    )
                    Text(
                        "????????????",
                        color = Black,
                        style = MaterialTheme.typography.overline,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Divider(color = LightGray, thickness = 0.5.dp)

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                value = inputSearchState,
                onValueChange = {
                    inputSearchState = it
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = LightGray,
                    unfocusedBorderColor = LightGray
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        movieViewModel.getMovies(inputSearchState)
                    }
                ),
                trailingIcon = {
                    Icon(Icons.Filled.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier
                            .clickable {
                                inputSearchState = ""
                            }
                    )
                }
            )

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

        if (movieState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .semantics { testTag = "progress" })
        }
    }
}

