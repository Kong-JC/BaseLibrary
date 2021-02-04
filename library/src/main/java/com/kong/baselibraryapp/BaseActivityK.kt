package com.jetosend.baselibrary

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivityK<B : ViewDataBinding>(
    @LayoutRes private val layout: Int
) : AppCompatActivity() {

    lateinit var bind: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, layout)
        bind.lifecycleOwner = this
    }

    override fun onDestroy() {
        super.onDestroy()
        bind.unbind()
    }

}