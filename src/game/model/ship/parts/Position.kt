package game.model.ship.parts

import game.engine.math.Degree
import game.model.Coords

class Position(
        var coords: Coords,
        var rotationX: Degree = Degree.ZERO,
        var rotationY: Degree = Degree.ZERO
)