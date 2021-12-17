package org.sopt.androidassingment.util.extension

import android.content.Context
import android.widget.Toast

fun Context.shotToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}