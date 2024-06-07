package com.myaxa.hotel_details_impl.util

import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import androidx.annotation.Px
import coil.size.Size
import coil.transform.Transformation

/*
* Inspired by https://github.com/Commit451/coil-transformations/blob/main/transformations/src/main/java/com/commit451/coiltransformations/Util.kt
* */
internal class CropTransformation(
    @Px private val left: Int = 1,
    @Px private val right: Int = 1,
    @Px private val top: Int = 1,
    @Px private val bottom: Int = 1,
) : Transformation {

    companion object {
        private val DEFAULT_PAINT = Paint(Paint.DITHER_FLAG or Paint.FILTER_BITMAP_FLAG)
    }

    constructor(@Px borderSize: Int) : this(borderSize, borderSize, borderSize, borderSize)

    constructor(@Px horizontal: Int, @Px vertical: Int) : this(horizontal, horizontal, vertical, vertical)

    private val Bitmap.safeConfig: Bitmap.Config
        get() = config ?: Bitmap.Config.ARGB_8888

    override val cacheKey: String = CropTransformation::class.java.name

    override suspend fun transform(input: Bitmap, size: Size): Bitmap {

        val m = Matrix()
        m.setTranslate(-left.toFloat(), -top.toFloat())

        val width = input.width - (left + right)
        val height = input.height - (top + bottom)
        val result = createBitmap(width, height, input.safeConfig)

        // We don't add or remove alpha, so keep the alpha setting of the Bitmap we were given.
        setAlpha(input, result)

        applyMatrix(input, result, m)

        return result
    }

    /**
     * Sets the alpha of the Bitmap we're going to re-use to the alpha of the Bitmap we're going to
     * transform. This keeps [android.graphics.Bitmap.hasAlpha]} consistent before and after
     * the transformation for transformations that don't add or remove transparent pixels.
     *
     * @param inBitmap The [android.graphics.Bitmap] that will be transformed.
     * @param outBitmap The [android.graphics.Bitmap] that will be returned from the transformation.
     */
    private fun setAlpha(inBitmap: Bitmap, outBitmap: Bitmap) {
        outBitmap.setHasAlpha(inBitmap.hasAlpha())
    }

    private fun applyMatrix(inBitmap: Bitmap, targetBitmap: Bitmap, matrix: Matrix) {
        try {
            val canvas = Canvas(targetBitmap)
            canvas.drawBitmap(inBitmap, matrix, DEFAULT_PAINT)
            clear(canvas)
        } finally {
        }
    }

    // Avoids warnings in M+.
    private fun clear(canvas: Canvas) {
        canvas.setBitmap(null)
    }
}