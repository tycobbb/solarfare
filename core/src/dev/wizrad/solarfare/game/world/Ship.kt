package dev.wizrad.solarfare.game.world

import com.badlogic.gdx.math.Vector2
import dev.wizrad.solarfare.game.renderer.core.CameraTrackable
import dev.wizrad.solarfare.game.renderer.support.toRectangle
import dev.wizrad.solarfare.game.world.core.Entity
import dev.wizrad.solarfare.generation.ShipNode

class Ship(
  node: ShipNode): Entity<ShipNode>(node), CameraTrackable {

  // MARK: Geometry
  val rect = node.center.toRectangle(width = 6.0f, height = 6.0f)

  // MARK: CameraTrackable
  override val point: Vector2 get() = rect.getCenter(Vector2.Zero)
}
