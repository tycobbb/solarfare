package dev.wizrad.solarfare.game.world

import com.badlogic.gdx.math.Vector2
import dev.wizrad.solarfare.game.core.update
import dev.wizrad.solarfare.game.renderer.core.CameraTrackable
import dev.wizrad.solarfare.game.renderer.support.set
import dev.wizrad.solarfare.game.world.core.NodeEntity
import dev.wizrad.solarfare.generation.SpaceNode
import dev.wizrad.solarfare.support.Tag
import dev.wizrad.solarfare.support.debug

class Space(
  node: SpaceNode): NodeEntity<SpaceNode>(node) {

  // MARK: Children
  lateinit var ship:    Ship
  lateinit var systems: List<SolarSystem>

  // MARK: Geometry
  val size = Vector2(0.0f, 0.0f)

  // MARK: Lifecycle
  init {
    // geometry
    size.set(node.size)
    center.set(size.x / 2, size.y / 2)

    // logs
    debug(Tag.World, "$this created")
  }

  override fun update(delta: Float) {
    super.update(delta)
    ship.update(delta)
    systems.update(delta)
  }

  // MARK: Accessors
  val trackable: CameraTrackable get() = ship

  // MARK: Debugging
  override fun toString(): String {
    return "[space size=$size]"
  }
}
