package engine.math

enum class Unit(
        private val scale: Long
) {

    AU(150_000_0),
    THOUSAND_KM(1000),
    KM(1);

    fun convertTo(newUnit: Unit): Float {
        return scale / newUnit.scale.toFloat()
    }
}