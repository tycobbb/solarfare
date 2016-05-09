package dev.wizrad.solarfare.game.world

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.game.renderer.core.CameraTrackable
import dev.wizrad.solarfare.game.ui.Minimap
import dev.wizrad.solarfare.game.ui.MinimapNode
import dev.wizrad.solarfare.game.world.core.NodeEntity
import dev.wizrad.solarfare.generation.ShipNode

class Ship(
  node:    ShipNode,
  parent:  Entity,
  minimap: Minimap): NodeEntity<ShipNode>(node, parent, minimap), CameraTrackable {

  // MARK: Lifecycle
  init {
    center.set(transform(node.center))
  }

  override fun configure(node: MinimapNode) {
    super.configure(node)
    node.color = Color.GREEN
  }

  // MARK: CameraTrackable
  override val point: Vector2 get() = center
}
