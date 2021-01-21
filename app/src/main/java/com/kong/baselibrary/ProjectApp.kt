package com.kong.baselibrary

import android.app.Application

class ProjectApp:Application() {
    override fun onCreate() {
        super.onCreate()
        BaseLibrary.init(this)
    }
}