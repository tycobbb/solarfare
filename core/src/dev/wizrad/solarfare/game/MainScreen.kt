package dev.wizrad.solarfare.game

import com.badlogic.gdx.Screen
import dev.wizrad.solarfare.generation.SpaceNode
import dev.wizrad.solarfare.generation.core.Root
import javax.inject.Inject

class MainScreen @Inject constructor (
  val root: Root<SpaceNode>): Screen {

  override fun render(delta: Float) {
  }

  override fun resize(width: Int, height: Int) {
  }

  override fun show() {
    // bootstrap world generation
    root.bootstrap()
  }

  override fun hide() {
  }

  override fun pause() {
  }

  override fun resume() {
  }

  override fun dispose() {
  }
}
