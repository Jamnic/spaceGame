package model.type

enum class DrawableResolution(
        private val resolution: Int,
        private val distance: Float
) {

    VERY_CLOSE(64, 1f),
    CLOSE(32, 0.1f),
    MEDIUM(24, 0.01f),
    FAR(12, 0.001f),
    VERY_FAR(8, 0.0001f),
    INVISIBLE(0, 0.0f);

    fun distance(): Float {
        return distance
    }

    fun resolution() : Int {
        return resolution
    }

    companion object {

        fun determineResolution(ratio : Float) : DrawableResolution {
            return values().firstOrNull { ratio > it.distance() } ?: DrawableResolution.INVISIBLE
        }
    }
}