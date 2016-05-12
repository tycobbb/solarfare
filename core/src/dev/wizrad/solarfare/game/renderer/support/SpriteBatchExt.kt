package dev.wizrad.solarfare.game.renderer.support

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch

inline fun SpriteBatch.with(tint: Color, crossinline closure: () -> Unit) {
  val previous = color

  color = tint
  closure()
  color = previous
}
