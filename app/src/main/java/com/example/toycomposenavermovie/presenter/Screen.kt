package com.example.toycomposenavermovie.presenter

sealed class Screen(val route: String) {
    object NaverListScreen: Screen("naver_list_screen")
    object NaverDetailScreen: Screen("naver_detail_screen")
}