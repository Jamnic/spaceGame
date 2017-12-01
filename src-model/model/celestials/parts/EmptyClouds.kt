package model.celestials.parts

import com.fasterxml.jackson.annotation.JsonCreator
import engine.math.Radius
import engine.math.Unit.KM
import javax.media.opengl.GL2

class EmptyClouds
@JsonCreator
constructor(
) : Clouds(null, Radius(0f, KM)) {

    override fun draw(gl: GL2) {
        // do nothing
    }
}