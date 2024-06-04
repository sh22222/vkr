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

        // When the screen is swiped up at the bottom
        // of the application, the navigationBar shall
        // appear for some time
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}