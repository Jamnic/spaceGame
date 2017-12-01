package model.ship.parts

import engine.math.Degree

class Engine(
        var rotationSpeed: Float,
        var velocity: Float = 0.toFloat(),
        var acceleration: Float = 0.toFloat(),
        var rotationXChange: Degree = Degree.ZERO,
        var rotationYChange: Degree = Degree.ZERO
)