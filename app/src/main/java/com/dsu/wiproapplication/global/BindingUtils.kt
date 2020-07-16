package com.dsu.wiproapplication.global

import android.graphics.drawable.Drawable
import android.text.TextUtils
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.RequestOptions
import com.dsu.GlideApp

/**
 * Created by Shobhit Gupta on 10, July, 2020
 * fiitjeeshobhit@gmail.com
 */
object BindingUtils {

    /**
     *This method display image from server url
     */
    @BindingAdapter("imageUrl", "placeHolder", "errorHolder")
    @JvmStatic
    fun loadImage(
        view: AppCompatImageView,
        url: String?,
        placeHolder: Drawable,
        errorHolder: Drawable
    ) {
        if (TextUtils.isEmpty(url))
            view.setImageDrawable(placeHolder)
        else
            GlideApp.with(view.context).load(url)
                .apply(RequestOptions().placeholder(placeHolder).error(errorHolder)).into(view)
    }
}