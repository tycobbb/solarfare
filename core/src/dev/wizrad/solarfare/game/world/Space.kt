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
  lateinit var ship: Ship
  lateinit var solarSystems: List<SolarSystem>

  // MARK: Geometry
  val size = Vector2.Zero

  // MARK: Lifecycle
  init {
    // geometry
    size.set(node.size)
    center.set(size.x / 2, size.y / 2)

    // logs
    debug(Tag.WORLD, "space -> $size")
  }

  override fun update(delta: Float) {
    super.update(delta)
    ship.update(delta)
    solarSystems.update(delta)
  }

  // MARK: Accessors
  val trackable: CameraTrackable get() = ship
}
