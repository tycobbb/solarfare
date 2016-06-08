package dev.wizrad.solarfare.game.world

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.game.core.update
import dev.wizrad.solarfare.game.world.core.NodeEntity
import dev.wizrad.solarfare.generation.SolarSystemNode

class SolarSystem(
  node:   SolarSystemNode,
  parent: Entity,
  world:  World): NodeEntity<SolarSystemNode>(node, world, parent) {

  // MARK: Children
  lateinit var star:    Star
  lateinit var planets: List<Planet>

  // MARK: Geometry
  override val center: Vector2 = Vector2(0.0f, 0.0f)

  // MARK: Lifecycle
  init {
    // geometry
    center.set(transform(node.center))
  }

  override fun update(delta: Float) {
    super.update(delta)
    star.update(delta)
    planets.update(delta)
  }
}
