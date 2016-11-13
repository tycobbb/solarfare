package dev.wizrad.solarfare.game.world.core

import com.badlogic.gdx.physics.box2d.World
import dev.wizrad.solarfare.game.components.controls.Controls
import dev.wizrad.solarfare.game.ui.minimap.Minimap
import dev.wizrad.solarfare.game.world.*
import dev.wizrad.solarfare.generation.*
import javax.inject.Inject

class NodeEntityFactory @Inject constructor(
  private val world:    World,
  private val controls: Controls,
  private val minimap: Minimap) {

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
    return Ship(node, parent, world, minimap, controls)
  }

  fun entity(node: StarNode, parent: SolarSystem): Star {
    return Star(node, parent, world, minimap)
  }

  fun entity(node: PlanetNode, parent: SolarSystem): Planet {
    return Planet(node, parent, world, minimap)
  }
}
