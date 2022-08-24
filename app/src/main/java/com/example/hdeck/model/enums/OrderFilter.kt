package com.example.hdeck.model.enums

import androidx.annotation.IdRes
import com.example.hdeck.R

enum class OrderFilter(val str: String, @IdRes val id: Int) {
    Asc("asc", R.id.bubble_asc),
    Desc("desc", R.id.bubble_desc);
    companion object {
        fun from(@IdRes id: Int?): OrderFilter? = values().find { it.id == id }
    }
}
