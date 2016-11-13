package dev.wizrad.solarfare.game.ui.minimap

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Group
import dev.wizrad.solarfare.game.components.CoordinateSpace
import dev.wizrad.solarfare.game.components.Textures

class Minimap(
  private val textures: Textures): Group() {

  init {
    width  = 100.0f
    height = 100.0f

    CoordinateSpace.minimap = CoordinateSpace.create(scale = Vector2(width, height), isReflected = true)
  }

  // MARK: Lifecycle
  override fun act(delta: Float) {
    val padding = 10.0f
    x = stage.viewport.screenWidth - width - padding
    y = padding

    super.act(delta)
  }

  override fun draw(batch: Batch?, parentAlpha: Float) {
    batch?.draw(textures.minimap, x, y, width, height)
    super.draw(batch, parentAlpha)
  }

  // MARK: Tracking
  fun track(mappable: Mappable): MinimapNode {
    val node = MinimapNode(mappable, textures.minimapNode)
    addActor(node)
    return node
  }
}
