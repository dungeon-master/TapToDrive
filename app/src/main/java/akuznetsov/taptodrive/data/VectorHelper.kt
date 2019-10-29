package akuznetsov.taptodrive.data

import kotlin.math.acos

object VectorHelper {

    @JvmStatic
    fun getAngleDegreesBetweenVectors(vector1: Vector, vector2: Vector): Float {
        val carVectorLen = vector1.length()
        val destVectorLen = vector2.length()

        val scalar = vector2.scalar(vector1)
        val cos = scalar / (carVectorLen * destVectorLen)

        return Math.toDegrees(acos(cos).toDouble()).toFloat()
    }

    @JvmStatic
    fun getRotationSign(mainVector: Vector, targetVector: Vector): Int {
        val v = (mainVector.bx - mainVector.ax) * (targetVector.by - mainVector.ay) -
                (mainVector.by - mainVector.ay) * (targetVector.bx - mainVector.ax)

        return if (v > 0) 1 else -1
    }
}