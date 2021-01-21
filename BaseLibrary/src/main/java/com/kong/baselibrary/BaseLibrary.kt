package com.kong.baselibrary

import android.app.Application

lateinit var app: Application

object BaseLibrary {
    fun init(application: Application) {
        app = application
    }
}