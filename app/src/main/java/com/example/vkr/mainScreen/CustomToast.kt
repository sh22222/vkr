package com.example.vkr.mainScreen

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
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
fun Toast.showCustomToast(text: String, activity: Activity, context: Context)
{
    val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val layout = inflater.inflate(R.layout.toast, activity?.findViewById(R.id.toastLayout))
    val tv = layout.findViewById<TextView>(R.id.tvToast)
    tv.setText(text)

    // use the application extension function
    this.apply {
        setGravity(Gravity.BOTTOM, 0, 40)
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }
}