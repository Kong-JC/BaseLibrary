package com.kong.app

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.afollestad.assent.Permission
import com.jetosend.baselibrary.BaseActivityK
import com.jetosend.baselibrary.toast
import com.kong.app.databinding.ActivityMainBinding
import com.kong.baselibraryapp.ext.*
import kotlinx.coroutines.launch

class MainActivity : BaseActivityK<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            requestPermissions(
                Permission.READ_PHONE_STATE, Permission.READ_EXTERNAL_STORAGE,
                Permission.WRITE_EXTERNAL_STORAGE
            ).yes {
//                "权限请求成功!".toast()
                R.string.permissionsRequestSuccess.toast()
            }.otherwise {
//                "权限请求失败!".toast()
                R.string.permissionsRequestFailure.toast()
            }
        }

    }

}