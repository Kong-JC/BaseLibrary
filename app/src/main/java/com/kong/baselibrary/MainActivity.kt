package com.kong.baselibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jetosend.baselibrary.getStrRes

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        0.getStrRes()
    }
}