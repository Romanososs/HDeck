package com.example.hdeck.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.ListPopupWindow
import com.example.hdeck.R
import com.example.hdeck.model.NameableEntity

class DropDownMenu(
    private val context: Context,
    container: View,
    data: List<Any> = emptyList(),
    onItemSelect: (Int) -> Unit
): ListPopupWindow(context, null, com.google.android.material.R.attr.listPopupWindowStyle) {
    init {
        anchorView = container
        height = 550
        setData(data)
        setOnItemClickListener { _, _, position: Int, _ ->
            onItemSelect(position)
            dismiss()
        }
    }
    fun setData(data: List<Any>){
        setAdapter(ArrayAdapter(context, R.layout.list_popup_window_item, data))
    }
}