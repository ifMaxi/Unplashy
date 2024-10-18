package com.maxidev.unplashy.utils

import android.content.Context
import android.widget.Toast

// Function to show a toast message
fun toastUtil(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT): Toast {

    return Toast.makeText(context, message, duration)
}