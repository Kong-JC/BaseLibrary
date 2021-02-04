package com.jetosend.baselibrary

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

class BaseDialogK<DB : ViewDataBinding>(
    context: Context,
    @LayoutRes private val layoutId: Int,
    @StyleRes private val themeResId: Int = 0,
    val onViewCreate: ((bind: DB, dialog: Dialog) -> Unit)
) : Dialog(context, themeResId) {

    init {
        show()
    }

    lateinit var bind: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, null, false)
        setContentView(bind.root)
        onViewCreate(bind, this)
        setOnKeyListener { dialog, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
                cancel()
            }
            false
        }
    }

    override fun dismiss() {
        super.dismiss()
        bind.unbind()
    }

}