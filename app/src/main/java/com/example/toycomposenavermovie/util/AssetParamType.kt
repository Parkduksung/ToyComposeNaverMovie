package com.example.toycomposenavermovie.util

import android.os.Bundle
import androidx.navigation.NavType
import com.example.toycomposenavermovie.domain.model.NaverMovie
import com.google.gson.Gson

class AssetParamType : NavType<NaverMovie>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): NaverMovie? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): NaverMovie {
        return Gson().fromJson(value, NaverMovie::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: NaverMovie) {
        bundle.putParcelable(key, value)
    }
}