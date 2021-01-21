package com.jetosend.baselibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding> (@LayoutRes val layoutId: Int) : Fragment() {

    lateinit var bind: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, layoutId, container, false)
        bind.lifecycleOwner = this
        return bind.root
    }

    override fun onDestroy() {
        super.onDestroy()
        bind.unbind()
    }

}