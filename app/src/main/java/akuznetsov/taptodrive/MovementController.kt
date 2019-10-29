package akuznetsov.taptodrive

import akuznetsov.taptodrive.data.Vector
import akuznetsov.taptodrive.data.VectorHelper
import akuznetsov.taptodrive.view.CarView
import akuznetsov.taptodrive.view.DestinationView
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.PointF
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator

class MovementController(private val mCar: CarView) {

    private var mMovementInProgress = false

    private var mOnDestinationReachedListener: ((destinationView: DestinationView) -> Unit)? = null

    private var mCarCenter = PointF(0f, 0f)
    private var mCarVector = Vector(0f, 0f, 0f, 0f)

    private var mDestinationView: DestinationView? = null

    fun initCarCenter() {
        val carCenterX = mCar.x + mCar.width / 2
        val carCenterY = mCar.y + mCar.height / 2

        mCarCenter = PointF(carCenterX, carCenterY)
        mCarVector = Vector(carCenterX, carCenterY, carCenterX, 0f)
    }

    fun setOnDestinationReachedListener(listener: ((destinationView: DestinationView) -> Unit)?) {
        mOnDestinationReachedListener = listener
    }

    fun isMovementInProgress() = mMovementInProgress

    fun startMovement(destination: PointF, destinationView: DestinationView) {
        mMovementInProgress = true
        mDestinationView = destinationView

        rotateAndMoveTo(destination)
    }

    private fun rotateAndMoveTo(destination: PointF) {
        val destVector = Vector(
            mCarCenter.x,
            mCarCenter.y,
            destination.x,
            destination.y
        )

        val rotation = VectorHelper.getAngleDegreesBetweenVectors(mCarVector, destVector)

        mCar.animate()
            .rotationBy(rotation * VectorHelper.getRotationSign(mCarVector, destVector))
            .setInterpolator(LinearInterpolator())
            .setDuration(ROTATION_DURATION)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    mCarVector = destVector
                    moveTo(destination)
                }
            })
            .start()
    }

    private fun moveTo(destination: PointF) {
        mCar.animate()
            .translationXBy(destination.x - mCarCenter.x)
            .translationYBy(destination.y - mCarCenter.y)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration(MOVE_DURATION)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    mCarCenter = destination
                    notifyMovementFinished()
                }
            })
            .start()
    }

    private fun notifyMovementFinished() {
        mMovementInProgress = false

        mDestinationView?.let {
            mOnDestinationReachedListener?.invoke(it)
        }
    }

    companion object {
        private const val ROTATION_DURATION = 500L
        private const val MOVE_DURATION = 1000L
    }
}