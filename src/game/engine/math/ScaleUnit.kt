package game.engine.math

enum class ScaleUnit(
        private val scale: Double
) {

    AU(150_000_0.0),
    THOUSAND_KM(1000.0),
    KM(1.0),
    THOUSANDTH_KM(0.001),
    MILLIONTH_KM(0.000001);

    fun convertTo(newUnit: ScaleUnit): Double {
        return scale / newUnit.scale
    }
}