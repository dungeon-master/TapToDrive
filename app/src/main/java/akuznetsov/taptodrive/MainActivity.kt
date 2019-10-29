package akuznetsov.taptodrive

import akuznetsov.taptodrive.view.CarView
import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.FrameLayout.LayoutParams

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_layout)

        val mainFrame = findViewById<FrameLayout>(R.id.main_frame)

        val carView = CarView(this)
        val carLayoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        carLayoutParams.gravity = Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL

        mainFrame.addView(carView, carLayoutParams)

        val movementController = MovementController(carView)
        movementController.setOnDestinationReachedListener { destinationView ->
            mainFrame.removeView(destinationView)
        }

        val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                movementController.initCarCenter()
                carView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        }
        carView.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)

        mainFrame.setOnTouchListener(FrameTouchListener(mainFrame, movementController))
    }
}