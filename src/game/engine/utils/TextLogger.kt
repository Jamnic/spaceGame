package game.engine.utils

import game.architecture.Constants.K

/**
 * Logger utility.
 */
object TextLogger {

    fun twoDecimal(number: Double): String {
        val suffix = StringBuilder()
        var number = number
        while (number > K || number < -K) {
            suffix.append("k")
            number /= K.toFloat()
        }

        return String.format("%.2f" + suffix, number)
    }
}