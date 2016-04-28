package dev.wizrad.solarfare.game.world

import com.badlogic.gdx.math.Vector2
import dev.wizrad.solarfare.game.renderer.core.CameraTrackable
import dev.wizrad.solarfare.game.renderer.support.set
import dev.wizrad.solarfare.game.world.core.NodeEntity
import dev.wizrad.solarfare.game.world.core.update
import dev.wizrad.solarfare.game.world.support.default
import dev.wizrad.solarfare.game.world.support.entities
import dev.wizrad.solarfare.generation.ShipNode
import dev.wizrad.solarfare.generation.SolarSystemNode
import dev.wizrad.solarfare.generation.SpaceNode
import dev.wizrad.solarfare.support.Tag
import dev.wizrad.solarfare.support.debug
import dev.wizrad.solarfare.support.reduce
import dev.wizrad.solarfare.support.zip

class Space(
  node: SpaceNode): NodeEntity<SpaceNode>(node, null) {

  // MARK: Children
  val ship: Ship
  val solarSystems: List<SolarSystem>

  // MARK: Geometry
  val size = Vector2.Zero

  // MARK: Lifecycle
  init {
    // geometry
    size.set(node.size)
    center.set(size.x / 2, size.y / 2)

    // logs
    debug(Tag.WORLD, "space -> $size")

    // children
    val children = reduce(node.children, default(), zip(
      entities { n: ShipNode -> Ship(n, this) },
      entities { n: SolarSystemNode -> SolarSystem(n, this) }
    ))

    ship         = children.first.first()
    solarSystems = children.second
  }

  override fun update(delta: Float) {
    super.update(delta)
    ship.update(delta)
    solarSystems.update(delta)
  }

  // MARK: Accessors
  val trackable: CameraTrackable get() = ship
}
