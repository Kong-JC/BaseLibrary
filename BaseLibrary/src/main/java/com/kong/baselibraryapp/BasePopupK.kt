package com.jetosend.baselibrary

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

open class BasePopupK(
    val context: Context,
    @LayoutRes val layoutId: Int,
    onViewCreate: ((view: View, pop: PopupWindow) -> Unit)
) {

    var view = LayoutInflater.from(context).inflate(layoutId, null, false)

    init {
        val popWnd = PopupWindow(context)
        popWnd.contentView = view
        popWnd.width = ViewGroup.LayoutParams.WRAP_CONTENT
        popWnd.height = ViewGroup.LayoutParams.WRAP_CONTENT
//        if (initLayout != null) initLayout.invoke(view, popWnd) else {
//        }
        popWnd.isOutsideTouchable = true
        onViewCreate(view,popWnd)
    }

}