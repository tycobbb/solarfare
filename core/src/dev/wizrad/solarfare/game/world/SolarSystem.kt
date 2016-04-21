package dev.wizrad.solarfare.game.world

import dev.wizrad.solarfare.generation.PlanetNode
import dev.wizrad.solarfare.generation.SolarSystemNode
import dev.wizrad.solarfare.generation.StarNode
import dev.wizrad.solarfare.support.reduce
import dev.wizrad.solarfare.support.zip

class SolarSystem(
  node: SolarSystemNode) : Entity<SolarSystemNode>(node) {

  private val star: Star
  private val planets: List<Planet>

  init {
    val result = reduce(node.children, default(), zip(
      entities { n: StarNode -> Star(n) },
      entities { n: PlanetNode -> Planet(n) }
    ))

    star    = result.first.first()
    planets = result.second
  }
}
