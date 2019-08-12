package com.tech.drivychallenge.ui

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.widget.ImageView


class RoundedImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas) {
        val drawable = drawable ?: return

        if (width == 0 || height == 0) {
            return
        }
        val bitmap = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        canvas.drawBitmap(
            bitmap.toRounded(width, height),
            0f,
            0f,
            null
        )
    }

    fun Bitmap.toRounded(width: Int, height: Int): Bitmap {
        val scaleRatio = height.toFloat() / this.height.toFloat()
        val scaledBitmap = Bitmap.createScaledBitmap(
            this,
            (scaleRatio * width).toInt(),
            height,
            false
        )

        val output = Bitmap.createBitmap(
            scaledBitmap.width,
            scaledBitmap.height,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(output)

        val paint = Paint()
        val rect = Rect(
            0,
            0,
            scaledBitmap.width,
            scaledBitmap.height
        )

        paint.isAntiAlias = true
        paint.isFilterBitmap = true
        paint.isDither = true

        canvas.drawCircle(
            width / 2f,
            height / 2f,
            width / 2f,
            paint
        )
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(scaledBitmap, rect, rect, paint)

        return output
    }

}