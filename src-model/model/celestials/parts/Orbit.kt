package model.celestials.parts

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import engine.calculators.PhysicsCalculator
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
        var radius: Double,
        velocity: Double,
        var position: Double
) {
    @JsonIgnore
    var coords: Coords = Coords(0.0, 0.0, 0.0)
    @JsonIgnore
    var angularVelocity: Double = PhysicsCalculator.angularVelocity(radius, velocity)
}