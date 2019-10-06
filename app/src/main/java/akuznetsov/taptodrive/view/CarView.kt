package akuznetsov.taptodrive.view

import akuznetsov.taptodrive.R
import android.content.Context
import android.util.AttributeSet
import android.view.View

class CarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleRes) {

    private val mHeight: Int = resources.getDimensionPixelSize(R.dimen.carHeight)
    private val mWidth: Int = resources.getDimensionPixelSize(R.dimen.carWidth)

    init {
        setBackgroundResource(R.color.colorCar)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(
            MeasureSpec.makeMeasureSpec(mWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY))
    }
}