package com.example.hdeck.utils

import android.text.Html
import android.text.Spanned

fun stringFromHTML(text: String?): Spanned {
    return Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
}