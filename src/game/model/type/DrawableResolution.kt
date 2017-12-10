package game.model.type

enum class DrawableResolution(
        private val resolution: Int,
        private val distance: Double
) {

    VERY_CLOSE(64, 50.0),
    CLOSE(32, 100.0),
    MEDIUM(24, 150.0),
    FAR(12, 200.0),
    VERY_FAR(8, 300.0),
    INVISIBLE(0, 0.0);

    fun distance(): Double {
        return distance
    }

    fun resolution(): Int {
        return resolution
    }

    companion object {
        fun determineResolution(ratio: Double): DrawableResolution {
            return values().firstOrNull { ratio < it.distance() } ?: DrawableResolution.INVISIBLE
        }
    }
}