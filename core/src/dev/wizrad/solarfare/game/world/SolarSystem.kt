package dev.wizrad.solarfare.game.world

import dev.wizrad.solarfare.game.world.core.Entity
import dev.wizrad.solarfare.game.world.core.NodeEntity
import dev.wizrad.solarfare.game.world.core.update
import dev.wizrad.solarfare.game.world.support.default
import dev.wizrad.solarfare.game.world.support.entities
import dev.wizrad.solarfare.generation.PlanetNode
import dev.wizrad.solarfare.generation.SolarSystemNode
import dev.wizrad.solarfare.generation.StarNode
import dev.wizrad.solarfare.support.reduce
import dev.wizrad.solarfare.support.zip

class SolarSystem(
  node: SolarSystemNode, parent: Entity) : NodeEntity<SolarSystemNode>(node, parent) {

  // MARK: Children
  val star: Star
  val planets: List<Planet>

  // MARK: Lifecycle
  init {
    // geometry
    center.set(transform(node.center))

    // children
    val result = reduce(node.children, default(), zip(
      entities { n: StarNode -> Star(n, this) },
      entities { n: PlanetNode -> Planet(n, this) }
    ))

    star    = result.first.first()
    planets = result.second
  }

  override fun update(delta: Float) {
    super.update(delta)
    star.update(delta)
    planets.update(delta)
  }
}
