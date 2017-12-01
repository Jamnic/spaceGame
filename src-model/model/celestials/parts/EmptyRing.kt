package model.celestials.parts

import com.fasterxml.jackson.annotation.JsonCreator
import engine.math.Radius
import engine.math.Unit
import javax.media.opengl.GL2

class EmptyRing
@JsonCreator
constructor(
) : Ring("", Radius(0f, Unit.KM), Radius(0f, Unit.KM)) {

    override fun draw(gl: GL2) {
        // do nothing
    }
}