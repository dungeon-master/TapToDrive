package akuznetsov.taptodrive

import akuznetsov.taptodrive.view.DestinationView
import android.graphics.PointF
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout

class FrameTouchListener(
    private val mFrame: FrameLayout,
    private val mMovementController: MovementController
) : View.OnTouchListener {

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (!mMovementController.isMovementInProgress() && event.action == MotionEvent.ACTION_DOWN) {
            val destinationView = DestinationView(mFrame.context)
            val destinationLayoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )

            destinationView.x = event.x
            destinationView.y = event.y

            mFrame.addView(destinationView, destinationLayoutParams)
            mMovementController.startMovement(PointF(event.x, event.y), destinationView)

            return true
        }

        return false
    }
}