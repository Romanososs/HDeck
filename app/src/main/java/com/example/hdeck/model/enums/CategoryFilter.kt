package com.example.hdeck.model.enums

import androidx.annotation.IdRes
import com.example.hdeck.R

enum class CategoryFilter(val str: String, @IdRes val id: Int) {
    Name("name", R.id.bubble_name),
    Attack("attack",R.id.bubble_attack),
    Health("health", R.id.bubble_health),
    Mana("manaCost", R.id.bubble_mana),
    Class("groupByClass", R.id.bubble_class);
    companion object {
        fun from(@IdRes id: Int?): CategoryFilter? = values().find { it.id == id }
    }
}