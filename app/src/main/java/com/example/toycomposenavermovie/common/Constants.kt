package com.example.toycomposenavermovie.common

object Constants {
    const val BASE_NAVER_URL = "https://openapi.naver.com/"
    const val CLIENT_ID = "qRkvFxV_lat8Ge_LbatQ"
    const val SECRET_KEY = "cVIQ3iWHU7"
}


sealed class ItemClickType {
    object LoadUrl : ItemClickType()
    object Bookmark : ItemClickType()
}