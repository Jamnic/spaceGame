package model.celestials.parts

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import engine.calculators.PhysicsCalculator
import engine.math.Degree
import engine.math.Radius
import model.Coords
import model.celestials.CelestialBody

/**
 * Object which stores the orbit parameters for [CelestialBody] entity.
 *
 * TODO It should be upgraded by:<br></br>
 * - ellipse<br></br>
 * - inclination<br></br>
 */
class Orbit
@JsonCreator
constructor(
        var orbitting: CelestialBody?,
        var radius: Radius,
        velocity: Float,
        var position: Degree
) {
    @JsonIgnore
    var coords: Coords = Coords(0.0, 0.0, 0.0)
    @JsonIgnore
    var angularVelocity: Float = PhysicsCalculator.angularVelocity(radius.value(), velocity)
}