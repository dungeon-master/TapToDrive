package akuznetsov.taptodrive.view

import akuznetsov.taptodrive.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.withTranslation

class DestinationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleRes) {

    private val mSize = resources.getDimensionPixelSize(R.dimen.destinationSize)
    private val mRadius = mSize / 2f

    private val mFillPaint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.colorDestination)
        style = Paint.Style.FILL
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(
            MeasureSpec.makeMeasureSpec(mSize, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(mSize, MeasureSpec.EXACTLY))
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.withTranslation(mRadius, mRadius) {
            drawCircle(0f, 0f, mRadius, mFillPaint)
        }
    }
}