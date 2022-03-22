package com.example.toycomposenavermovie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.toycomposenavermovie.presenter.Screen
import com.example.toycomposenavermovie.presenter.bookmark.BookmarkScreen
import com.example.toycomposenavermovie.presenter.detail.MovieDetailScreen
import com.example.toycomposenavermovie.presenter.list.MovieListScreen
import com.example.toycomposenavermovie.ui.theme.ToyComposeNaverMovieTheme
import com.example.toycomposenavermovie.util.AssetParamType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToyComposeNaverMovieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.NaverListScreen.route
                    ) {
                        composable(
                            route = Screen.NaverListScreen.route
                        ) {
                            MovieListScreen(
                                navController = navController
                            )
                        }

                        composable(
                            route = Screen.NaverDetailScreen.route + "/{movie}",
                            arguments = listOf(
                                navArgument("movie") {
                                    type = AssetParamType()
                                }
                            )
                        ) { MovieDetailScreen() }

                        composable(
                            route = Screen.BookmarkScreen.route
                        ) {
                            BookmarkScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}