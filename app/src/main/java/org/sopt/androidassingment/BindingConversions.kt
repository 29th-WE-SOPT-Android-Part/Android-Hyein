package org.sopt.androidassingment

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingConversions {
    @BindingAdapter("imageSet")
    @JvmStatic
    fun loadImage(imageView : ImageView, url : String){
        Glide.with(imageView.context)
            .load(url)
            .circleCrop()
            .into(imageView)
    }
}