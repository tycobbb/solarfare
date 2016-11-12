package dev.wizrad.solarfare.game.ui.minimap

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Group
import dev.wizrad.solarfare.game.shared.CoordinateSpace.Kind
import dev.wizrad.solarfare.game.shared.Textures
import dev.wizrad.solarfare.game.shared.coordinateSpace

class Minimap(
  private val textures: Textures): Group() {

  init {
    width  = 100.0f
    height = 100.0f

    // setup the minimap coordinate space
    coordinateSpace(Kind.Minimap, byScale = Vector2(width, height), reflected = true)
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
