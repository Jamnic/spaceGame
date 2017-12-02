package engine.math

class Circuit(
        value: Float,
        unit: ScaleUnit
) : Distance(value, unit) {

    fun length(): Float {
        return value
    }
}