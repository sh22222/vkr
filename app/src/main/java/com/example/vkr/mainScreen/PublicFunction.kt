package com.example.vkr.mainScreen

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.vkr.R
import java.math.BigInteger
import java.security.MessageDigest

fun Toast.showCustomToast(text: String, activity: Activity)
{
    val layout = activity.layoutInflater.inflate (
        R.layout.toast,
        activity.findViewById(R.id.toastLayout)
    )

    // set the text of the TextView of the message
    val textView = layout.findViewById<TextView>(R.id.tvToast)
    textView.text = text

    // use the application extension function
    this.apply {
        setGravity(Gravity.BOTTOM, 0, 40)
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }
}
@RequiresApi(Build.VERSION_CODES.R)
fun hideSystemUI(window: Window) {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window,
        window.decorView.findViewById(android.R.id.content)).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}
@OptIn(ExperimentalStdlibApi::class)
fun String.md5():String{
    val md = MessageDigest.getInstance("MD5")
    val result = md.digest(this.toByteArray())
    return result.toHexString()
}