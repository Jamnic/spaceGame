package game.model.interfaces

import game.graphics.drawers.OpenGL

interface Drawable {
    fun draw(openGL: OpenGL)
}