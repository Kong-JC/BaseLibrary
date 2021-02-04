package com.jetosend.baselibrary

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.kong.baselibraryapp.app
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors

private const val Y = "YYYY"
private const val M = "MM"
private const val D = "DD"
private const val HH = "HH"
private const val MM = "MM"
private const val SS = "SS"

/** 获取 YYYY-MM-DD HH:MM:SS 格式字符串 */
fun getTimeFormatStrYMDHMS(
    date: Date, dateSeparator: String = "-", timeSeparator: String = ":"
): String = SimpleDateFormat(
    "$Y$dateSeparator$M$dateSeparator$D $HH$timeSeparator$MM$timeSeparator$SS"
).format(date)

fun getTimeFormatStrYMDHMS(
    time: Long, dateSeparator: String = "-", timeSeparator: String = ":"
): String = getTimeFormatStrYMDHMS(Date(time), dateSeparator, timeSeparator)

/** 获取 YYYYMMDDHHMMSS 字符串 */
fun getTimeStrYMDHMS(
    date: Date
): String = SimpleDateFormat("$Y$M$D$HH$MM$SS").format(date)

fun getTimeStrYMDHMS(
    time: Long
): String = getTimeStrYMDHMS(Date(time))


//fun getNowTimeFromHM(
//    nowTime: Long? = System.currentTimeMillis()
//): String = SimpleDateFormat(HH_MM).format(nowTime)
//
//fun getNowTimeFromHMLater5(): String = getNowTimeFromHM(
//    System.currentTimeMillis() + 5 * 1000 * 60
//)
//
//fun getNowTimeFromYMDHM(
//    nowTime: Long? = System.currentTimeMillis()
//): String = SimpleDateFormat(YYYY_MM_DD_HH_MM).format(nowTime)
//
//fun getNowTimeFromYMDLater5() = getNowTimeFromYMDHM(
//    System.currentTimeMillis() + 5 * 1000 * 60
//)


fun getCurrentMonthDayNum(): Int {
    val a = Calendar.getInstance()
    a[Calendar.DATE] = 1
    a.roll(Calendar.DATE, -1)
    return a[Calendar.DATE]
}


private const val KB = 1024
private const val MB = KB * 1024
private const val GB = MB * 1024
private const val TB = GB * 1024

fun getFileSize(size: Int): String = getFileSize(size.toLong())

fun getFileSize(size: Long): String {
    return if (size > 1 && size < KB) "$size B"
    else if (size > KB && size < MB) DecimalFormat("#0.00").format(size.toDouble() / KB) + " KB"
    else if (size > MB && size < GB) DecimalFormat("#0.00").format(size.toDouble() / MB) + " MB"
    else if (size > GB && size < TB) DecimalFormat("#0.00").format(size.toDouble() / GB) + " GB"
    else "0"
}

val h = 1000 / 60 / 60 % 60
val m = 1000 / 60 % 60
val s = 1000 % 60

fun getMediaFormatTime(time: Int): String = getMediaFormatTime(time.toLong())
fun getMediaFormatTime(time: Float): String = getMediaFormatTime(time.toLong())
fun getMediaFormatTime(time: Double): String = getMediaFormatTime(time.toLong())

fun getMediaFormatTime(time: Long): String {
    val hStr = if (time / h == 0L) "00" else if (h < 10) "0$h" else h
    val mStr = if (time / m == 0L) "00" else if (m < 10) "0$m" else m
    val sStr = if (time / s == 0L) "00" else if (s < 10) "0$s" else s
    return "$hStr:$mStr:$sStr"
}

//fun getMediaTime(time: Long): String {
//    val sb = StringBuilder()
//    (time / 1000 / 60 / 60 % 60).takeIf { it > 0 }
//        ?.apply { sb.append(String.format("%02d:", this)) }
//    (time / 1000 / 60 % 60).takeIf { it > 0 }?.apply { sb.append(String.format("%02d:", this)) }
//    (time / 1000 % 60).takeIf { it > 0 }?.apply { sb.append(String.format("%02d", this)) }
////    (time / 10 % 100).takeIf { it > 0 }?.apply { sb.append(String.format("%02d", this)) }
//    return sb.toString()
//}

fun getSingleDigits(value: Int): Int = value % 10
fun getTenDigits(value: Int): Int = value / 10 % 10
fun getHundredsDigits(value: Int): Int = value / 100 % 10
fun getThousandsDigits(value: Int): Int = value / 1000 % 10

fun getColorEx(@ColorRes id: Int): Int = ContextCompat.getColor(app, id)
fun getStringEx(@StringRes id: Int): String = app.resources.getString(id)

fun ioThread(f: () -> Unit) = Executors.newSingleThreadExecutor().execute(f)

fun launch(block: suspend CoroutineScope.() -> Unit) = GlobalScope.launch(block = block)

fun dp2px(dp: Float): Int {
    val scale: Float = app.getResources().getDisplayMetrics().density
    return (dp * scale + 0.5f).toInt()
}

fun px2dp(px: Float): Int {
    val scale: Float = app.getResources().getDisplayMetrics().density
    return (px / scale + 0.5f).toInt()
}
