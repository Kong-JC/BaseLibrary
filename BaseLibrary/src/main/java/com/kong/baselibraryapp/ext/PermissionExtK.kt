package com.kong.baselibraryapp.ext

import android.app.Activity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.afollestad.assent.*
import com.afollestad.assent.rationale.RationaleHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

// copy from com.fmt.github project in 2021/2/4
//拓展Assent动态申请权限
suspend fun Activity.requestPermissions(
    vararg permissions: Permission,
    requestCode: Int = 40,
    rationaleHandler: RationaleHandler? = null
): Boolean = suspendCancellableCoroutine { continuation ->

    //这里注意：permissions可变参数，传递要加*，如果不太明白，可以反编译Kotlin查看编译后的Java类
    isAllGranted(*permissions).yes {
        continuation.resume(true)
    }.otherwise {
        askForPermissions(
            *permissions,
            requestCode = requestCode,
            rationaleHandler = rationaleHandler
        ) {
            it.isAllGranted(*permissions).yes {
                //granted.invoke(it)
                continuation.resume(true)
            }.otherwise {
                continuation.resume(false)
            }
        }
    }
}
