package dev.wizrad.solarfare.game.ui.minimap

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import dev.wizrad.solarfare.game.components.CoordinateSpace.Companion.minimap
import dev.wizrad.solarfare.game.components.CoordinateSpace.Companion.transform
import dev.wizrad.solarfare.game.components.CoordinateSpace.Companion.world
import dev.wizrad.solarfare.game.ui.support.setPosition

class MinimapNode(
  private val mappable: Mappable,
  private val texture: TextureRegion): Actor() {

  // MARK: Lifecycle
  init {
    width  = 2.0f
    height = 2.0f
  }

  override fun act(delta: Float) {
    super.act(delta)
    setPosition(transform(mappable.center, from = world, to = minimap))
  }

  override fun draw(batch: Batch?, parentAlpha: Float) {
    batch?.color = color
    batch?.draw(texture, x, y, width, height)
    super.draw(batch, parentAlpha)
  }

  fun destroy() {
    remove()
  }
}
