package engine.math

enum class ScaleUnit(
        private val scale: Float
) {

    AU(150_000_0F),
    THOUSAND_KM(1000F),
    KM(1F),
    THOUSANDTH_KM(0.001F);

    fun convertTo(newUnit: ScaleUnit): Float {
        return scale / newUnit.scale
    }
}