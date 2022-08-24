package com.example.hdeck.model

import com.example.hdeck.model.enums.CategoryFilter
import com.example.hdeck.model.enums.OrderFilter

data class Filter(
    val category: CategoryFilter = CategoryFilter.Name,
    val order: OrderFilter = OrderFilter.Asc
){
    fun getFilter(): String = "${category.str}:${order.str}"
}
