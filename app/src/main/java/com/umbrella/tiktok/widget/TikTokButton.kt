package com.umbrella.tiktok.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withTranslation
import com.umbrella.tiktok.R
import com.umbrella.tiktok.extension.getBitmap
import com.umbrella.tiktok.extension.spaceSmall
import com.umbrella.tiktok.extension.spaceTiny

class TikTokButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val spaceTiny = context.spaceTiny
    private val radius = context.spaceSmall
    private val rightColor = Color.parseColor("#E5436D")
    private val leftColor = Color.parseColor("#65D1E8")
    private val centerColor = Color.parseColor("#ffffff")
    private val rectF = RectF()
    private val plusIconBitmap = getBitmap(R.drawable.ic_plus)

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmapOfThisView, 0f, 0f, paint)
    }

    private fun getRightPaint() = paint.apply {
        color = rightColor
    }

    private fun getLeftPaint() = paint.apply {
        color = leftColor
    }

    private fun getCenterPaint() = paint.apply {
        color = centerColor
    }

    private val bitmapOfThisView by lazy {
        // 2. Create bitmap which has the same size as the view
        val bm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)

        // 3. Draw the right part
        rectF.set(0f, 0f, width.toFloat() - spaceTiny, height.toFloat())
        getRightPaint().let {
            canvas.withTranslation(x = spaceTiny) {
                drawRoundRect(rectF, radius, radius, it)
            }
        }

        // 4. Draw the center part
        getCenterPaint().let {
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP)
            canvas.drawRoundRect(rectF, radius, radius, it)
        }

        // 5. Draw the left part
        getLeftPaint().let {
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OVER)
            canvas.drawRoundRect(rectF, radius, radius, it)
        }

        // 6. Draw the plus icon
        plusIconBitmap.let {
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.XOR)
            canvas.drawBitmap(it, width / 2f - it.width / 2f, height / 2f - it.height / 2f, paint)
        }

        // 7. Clear the PorterDuffXfermode
        paint.xfermode = null
        bm
    }
}