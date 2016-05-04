package dev.wizrad.solarfare.game.ui

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor

class MinimapNode(
  private val mappable: Mappable): Actor() {

  // MARK: Properties
  val id: Int = nextId()

  // MARK: Lifecycle
  override fun act(delta: Float) {
    super.act(delta)
  }

  override fun draw(batch: Batch?, parentAlpha: Float) {
    super.draw(batch, parentAlpha)
  }

  fun destroy() {
    remove()
  }

  companion object {
    private var id = 0
    private fun nextId(): Int {
      return id++
    }
  }
}
