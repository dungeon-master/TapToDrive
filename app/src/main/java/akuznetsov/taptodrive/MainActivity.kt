package akuznetsov.taptodrive

import akuznetsov.taptodrive.view.CarView
import akuznetsov.taptodrive.view.DestinationView
import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
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

        mainFrame.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    val destinationView = DestinationView(this@MainActivity)
                    val destinationLayoutParams = LayoutParams(
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT)

                    destinationView.x = event.x
                    destinationView.y = event.y

                    mainFrame.addView(destinationView, destinationLayoutParams)

                    return true
                }

                return false
            }
        })
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}