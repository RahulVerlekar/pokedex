package com.rahulverlekar.pokedex.common

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.model.GlideUrl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

/**
 * Created by Mukesh Yadav on 24/10/20.
 */

@BindingAdapter("tintColor")
fun ImageView.setTint(@ColorRes colorRes: Int) {
    ImageViewCompat.setImageTintList(
        this, ColorStateList.valueOf(
            ContextCompat.getColor(
                context,
                colorRes
            )
        )
    )
}

@BindingAdapter("tintColor")
fun ImageView.setTint(color: String) {
    ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(Color.parseColor(color)))
}

fun AppCompatImageView.setTint(@ColorRes colorRes: Int) {
    ImageViewCompat.setImageTintList(
        this, ColorStateList.valueOf(
            ContextCompat.getColor(
                context,
                colorRes
            )
        )
    )
}

@BindingAdapter("src")
fun ImageView.setSrc(@DrawableRes res: Int) {
    this.setImageDrawable(resources.getDrawable(res, context.theme))
}

@BindingAdapter("src")
fun AppCompatImageView.setSrc(@DrawableRes res: Int) {
    this.setImageDrawable(resources.getDrawable(res, context.theme))
}

@BindingAdapter("src")
fun ImageView.setSrc(drawable: Drawable) {
    this.setImageDrawable(drawable)
}

@BindingAdapter("src")
fun AppCompatImageView.setSrc(drawable: Drawable) {
    this.setImageDrawable(drawable)
}


@BindingAdapter(
    "filePath", "scope",
    requireAll = false
)
fun AppCompatImageView.setPath(path: String, scope: CoroutineScope? = null) {
    path.let {
        val imgFile = File(path)
        if (imgFile.exists()) {
            if (scope != null) {
                scope.launch {
                    val myBitmap: Bitmap = withContext(Dispatchers.IO) {
                        BitmapFactory.decodeFile(imgFile.absolutePath)
                    }
                    setImageBitmap(myBitmap)
                }
            } else {
                /*val myBitmap: Bitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                setImageBitmap(myBitmap)*/
            }
        }
    }
}

@BindingAdapter(
    "glideSrc",
    "glideCenterCrop",
    "glideCircularCrop",
    "glidePlaceholder",
    requireAll = false
)
fun ImageView.bindGlideSrc(
    url: String?,
    centerCrop: Boolean = false,
    circularCrop: Boolean = false,
    @DrawableRes placeholder: Int?
) {
    if (url == null) return
    createGlideRequest(
        context,
        GlideUrl(url),
        centerCrop,
        circularCrop,
        placeholder
    ).into(this)
}

@BindingAdapter(
    "glideSrc",
    "glideCenterCrop",
    "glideCircularCrop",
    "glidePlaceholder",
    requireAll = false
)
fun AppCompatImageView.bindGlideSrc(
    url: String?,
    centerCrop: Boolean = false,
    circularCrop: Boolean = false,
    @DrawableRes placeholder: Int?
) {
    if (url == null) return

    createGlideRequest(
        context,
        GlideUrl(url),
        centerCrop,
        circularCrop,
        placeholder
    ).into(this)
}

@BindingAdapter("gif")
fun AppCompatImageView.loadGif(@RawRes glideGif: Int) {
    Glide.with(context)
        .load(glideGif)
        .into(this)
}

private fun createGlideRequest(
    context: Context,
    src: GlideUrl,
    centerCrop: Boolean,
    circularCrop: Boolean,
    @DrawableRes placeholder: Int?
): RequestBuilder<Drawable> {
    val req = Glide.with(context).load(src)
    placeholder?.let {
        req.placeholder(it)
    }
    if (centerCrop) req.centerCrop()
    if (circularCrop) req.circleCrop()
    return req
}
