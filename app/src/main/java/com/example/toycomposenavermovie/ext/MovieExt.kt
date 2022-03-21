package com.example.toycomposenavermovie.ext

import android.text.Html
import java.lang.Exception


fun String.convertHtml(): String =
    Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()

fun String.convertPersons(): String {
    return try {
        val toSplitAndJoinToString = this.split("|").joinToString(",")

        if (toSplitAndJoinToString.last() == ',') {
            toSplitAndJoinToString.substring(0, toSplitAndJoinToString.lastIndex)
        } else {
            toSplitAndJoinToString
        }
    } catch (e: Exception) {
        ""
    }
}



