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
    context: Context,
    container: View,
    data: List<Any>,
    onItemSelect: () -> Unit
): ListPopupWindow(context, null, com.google.android.material.R.attr.listPopupWindowStyle) {
    init {
        anchorView = container
        height = 550
        setAdapter(ArrayAdapter(context, R.layout.list_popup_window_item, data))
        setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            onItemSelect()
            dismiss()
        }
    }
}