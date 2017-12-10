package game.model.celestials.parts

import game.engine.math.Radius
import game.engine.math.ScaleUnit
import game.graphics.drawers.OpenGL

class EmptyRing : Ring("", Radius(0.0, ScaleUnit.KM), Radius(0.0, ScaleUnit.KM)) {

    override fun draw(openGL: OpenGL) {
        // do nothing
    }
}