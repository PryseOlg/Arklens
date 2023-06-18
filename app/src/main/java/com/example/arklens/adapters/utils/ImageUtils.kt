package com.example.arklens.adapters.utils
import android.util.Log
import android.widget.ImageView
import com.example.arklens.R
import com.squareup.picasso.Picasso


object ImageUtils {
    private fun loadImage(imageUrl: String, imageView: ImageView, placeholder: Int, errorResId: Int) {
        try {
            Picasso.get().load(imageUrl)
                .placeholder(placeholder)
                .error(errorResId)
                .into(imageView)
        } catch (ex: Exception) {
            Log.e("Error", ex.message.toString())
            ex.printStackTrace()
        }
    }

    fun loadCharacterPortrait(imageUrl: String, imageView: ImageView) {
        return loadImage(imageUrl, imageView, R.drawable.character_icon, R.drawable.character_icon)
    }

    fun loadItemIcon(imageUrl: String, imageView: ImageView) {
        return loadImage(imageUrl, imageView, R.drawable.character_icon, R.drawable.item_icon)
    }
}