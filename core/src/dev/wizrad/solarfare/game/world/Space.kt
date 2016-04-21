package dev.wizrad.solarfare.game.world

import dev.wizrad.solarfare.generation.ShipNode
import dev.wizrad.solarfare.generation.SolarSystemNode
import dev.wizrad.solarfare.generation.SpaceNode
import dev.wizrad.solarfare.support.reduce
import dev.wizrad.solarfare.support.zip

class Space(
  node: SpaceNode): Entity<SpaceNode>(node) {

  private val ship: Ship
  private val solarSystems: List<SolarSystem>

  init {
    val children = reduce(node.children, default(), zip(
      entities { n: ShipNode -> Ship(n) },
      entities { n: SolarSystemNode -> SolarSystem(n) }
    ))

    ship         = children.first.first()
    solarSystems = children.second
  }
}
