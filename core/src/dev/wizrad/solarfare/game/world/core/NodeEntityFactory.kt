package dev.wizrad.solarfare.game.world.core

import dev.wizrad.solarfare.game.minimap.Minimap
import dev.wizrad.solarfare.game.world.*
import dev.wizrad.solarfare.game.world.support.default
import dev.wizrad.solarfare.game.world.support.entities
import dev.wizrad.solarfare.generation.*
import dev.wizrad.solarfare.support.reduce
import dev.wizrad.solarfare.support.zip
import javax.inject.Inject

class NodeEntityFactory @Inject constructor(
  private val minimap: Minimap) {

  fun entity(node: SpaceNode): Space {
    val result = Space(node, minimap)

    val children = reduce(node.children, default(), zip(
      entities { n: ShipNode -> entity(n, result) },
      entities { n: SolarSystemNode -> entity(n, result) }
    ))

    result.ship         = children.first.first()
    result.solarSystems = children.second

    return result
  }

  fun entity(node: SolarSystemNode, parent: Space): SolarSystem {
    val result = SolarSystem(node, parent)

    val children = reduce(node.children, default(), zip(
      entities { n: StarNode -> entity(n, result) },
      entities { n: PlanetNode -> entity(n, result) }
    ))

    result.star    = children.first.first()
    result.planets = children.second

    return result
  }

  fun entity(node: ShipNode, parent: Space): Ship {
    return Ship(node, parent, minimap)
  }

  fun entity(node: StarNode, parent: SolarSystem): Star {
    return Star(node, parent, minimap)
  }

  fun entity(node: PlanetNode, parent: SolarSystem): Planet {
    return Planet(node, parent, minimap)
  }
}
