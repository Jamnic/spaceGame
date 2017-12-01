package model.ship.parts

import engine.math.Degree
import model.Coords

class Position(
        var coords: Coords,
        var rotationX: Degree = Degree.ZERO,
        var rotationY: Degree = Degree.ZERO
)