package game.model.ship.parts

import game.engine.math.Degree

class Engine(
        var velocity: Double = 0.0,
        var acceleration: Double = 0.0,
        var rotationXChange: Degree = Degree.ZERO,
        var rotationYChange: Degree = Degree.ZERO
)