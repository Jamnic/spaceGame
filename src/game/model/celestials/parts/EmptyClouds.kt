package game.model.celestials.parts

import game.engine.math.Radius
import game.engine.math.ScaleUnit.KM
import game.graphics.drawers.OpenGL

class EmptyClouds : Clouds(null, Radius(0.0, KM)) {

    override fun draw(openGL: OpenGL) {
        // do nothing
    }
}