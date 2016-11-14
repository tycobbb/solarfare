package dev.wizrad.solarfare.game.world.core

import dev.wizrad.solarfare.game.world.*
import dev.wizrad.solarfare.generation.*

class NodeEntityFactory(
  private val world: World) {

  fun entity(node: SpaceNode): Space {
    val result = Space(node, world)

    result.ship    = entity(node.ship, result)
    result.systems = node.systems.map { entity(it, result) }

    return result
  }

  fun entity(node: SolarSystemNode, parent: Space): SolarSystem {
    val result = SolarSystem(node, parent, world)

    result.star    = entity(node.star, result)
    result.planets = node.planets.map { entity(it, result) }

    return result
  }

  fun entity(node: ShipNode, parent: Space): Ship {
    return Ship(node, parent, world)
  }

  fun entity(node: StarNode, parent: SolarSystem): Star {
    return Star(node, parent, world)
  }

  fun entity(node: PlanetNode, parent: SolarSystem): Planet {
    return Planet(node, parent, world)
  }
}
