package dev.wizrad.solarfare.game.world

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import dev.wizrad.solarfare.game.core.update
import dev.wizrad.solarfare.game.renderer.core.CameraTrackable
import dev.wizrad.solarfare.game.renderer.support.set
import dev.wizrad.solarfare.game.world.core.NodeEntity
import dev.wizrad.solarfare.generation.SpaceNode
import dev.wizrad.solarfare.support.Tag
import dev.wizrad.solarfare.support.debug

class Space(
  node:  SpaceNode,
  world: World): NodeEntity<SpaceNode>(node, world) {

  // MARK: Children
  lateinit var ship:    Ship
  lateinit var systems: List<SolarSystem>

  // MARK: Geometry
  val size = Vector2(0.0f, 0.0f).set(node.size)

  // MARK: Lifecycle
  init {
    debug(Tag.World, "$this created")
  }

  override fun defineBody(node: SpaceNode): BodyDef {
    val body = super.defineBody(node)
    body.position.set(node.size * 0.5)
    return body
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
