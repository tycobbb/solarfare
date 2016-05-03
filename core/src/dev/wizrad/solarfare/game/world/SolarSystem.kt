package dev.wizrad.solarfare.game.world

import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.game.core.update
import dev.wizrad.solarfare.game.world.core.NodeEntity
import dev.wizrad.solarfare.generation.SolarSystemNode

class SolarSystem(
  node: SolarSystemNode, parent: Entity) : NodeEntity<SolarSystemNode>(node, parent) {

  // MARK: Children
  lateinit var star:    Star
  lateinit var planets: List<Planet>

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
