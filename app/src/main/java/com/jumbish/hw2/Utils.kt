package com.jumbish.hw2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

object Utils {
    fun showCustomToast(context: Context, message: String, iconRes : Int = R.drawable.ic_launcher_foreground){
        val inflater = LayoutInflater.from(context)
        val layout : View  = inflater.inflate(R.layout.custom_toast,null)
    val toastText = layout.findViewById<TextView>(R.id.toast_text)
        val toastImage = layout.findViewById<ImageView>(R.id.toast_icon)

        toastText.text = message
        toastImage.setImageResource(iconRes)

        val toast = Toast(context)
        toast.duration = Toast.LENGTH_LONG
        toast.view = layout
        toast.show()


    }



}