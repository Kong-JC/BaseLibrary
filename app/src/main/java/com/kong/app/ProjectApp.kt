package com.kong.app

import android.app.Application
import com.kong.baselibraryapp.BaseLibrary

/**
 * @Author:         Kong
 * @CreateDate:     2021/2/4 14:29
 */
class ProjectApp:Application() {
    override fun onCreate() {
        super.onCreate()
        BaseLibrary.init(this)
    }
}