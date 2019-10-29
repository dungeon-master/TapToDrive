package akuznetsov.taptodrive.data

import kotlin.math.sqrt

data class Vector(val ax: Float, val ay: Float, val bx: Float, val by: Float) {

    private fun getX() = bx - ax

    private fun getY() = by - ay

    fun length(): Float {
        val x = getX()
        val y = getY()
        return sqrt(x * x + y * y)
    }

    fun scalar(vector: Vector): Float {
        return getX() * vector.getX() + getY() * vector.getY()
    }
}