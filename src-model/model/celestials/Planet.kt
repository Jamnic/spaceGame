package model.celestials

import model.celestials.parts.*

class Planet(
        name: String,
        orbit: Orbit,
        clouds: Clouds?,
        ring: Ring?,
        sphere: Sphere
) : CelestialBody(name, orbit, sphere, clouds ?: EmptyClouds(), ring ?: EmptyRing())
