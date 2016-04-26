package dev.wizrad.solarfare.game.renderer.support

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.Rectangle

fun ShapeRenderer.rect(source: Rectangle) {
  rect(source.x, source.y, source.width, source.height)
}

inline fun ShapeRenderer.draw(type: ShapeType = ShapeType.Filled, crossinline closure: (ShapeRenderer) -> Unit) {
  begin(type)
  closure(this)
  end()
}
