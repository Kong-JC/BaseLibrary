package com.jetosend.baselibrary

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.kong.baselibrary.app
import java.io.File


fun String.toast() = Toast.makeText(app, this, Toast.LENGTH_SHORT).show()
fun String.toastLong() = Toast.makeText(app, this, Toast.LENGTH_LONG).show()
fun Int.toast() = Toast.makeText(app, this, Toast.LENGTH_SHORT).show()
fun Int.toastLong() = Toast.makeText(app, this, Toast.LENGTH_LONG).show()

fun View.onClick(method: (v: View) -> Unit) = this.setOnClickListener { method(this) }

fun View.setVisibility(visibility: Boolean) {
    this.visibility = if (visibility) View.VISIBLE else View.GONE
}

fun Int.getStrRes(): String = getStringEx(this)
fun Int.getColorRes(): Int = getColorEx(this)

fun ImageView.loadImage(url: String) = Glide.with(context).load(url).into(this)
fun ImageView.loadImage(uri: Uri) = Glide.with(context).load(uri).into(this)
fun ImageView.loadImage(file: File) = Glide.with(context).load(file).into(this)
fun ImageView.loadImage(resId: Int) = Glide.with(context).load(resId).into(this)

fun Any.toJson() = Gson().toJson(this)
fun <T> String.fromJson(clazz: Class<T>): T = Gson().fromJson(this, clazz)

