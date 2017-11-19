package model.celestials.parts

import com.fasterxml.jackson.annotation.JsonCreator
import javax.media.opengl.GL2

class EmptyRing
@JsonCreator
constructor(
) : Ring("", 0.0, 0.0) {

    override fun draw(gl: GL2) {
        // do nothing
    }
}