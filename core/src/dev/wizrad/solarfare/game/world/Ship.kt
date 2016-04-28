package dev.wizrad.solarfare.game.world

import com.badlogic.gdx.math.Vector2
import dev.wizrad.solarfare.game.renderer.core.CameraTrackable
import dev.wizrad.solarfare.game.world.core.Entity
import dev.wizrad.solarfare.game.world.core.NodeEntity
import dev.wizrad.solarfare.generation.ShipNode

class Ship(
  node: ShipNode, parent: Entity): NodeEntity<ShipNode>(node, parent), CameraTrackable {

  init {
    center.set(transform(node.center))
  }

  // MARK: CameraTrackable
  override val point: Vector2 get() = center
}
