package dev.wizrad.solarfare.game.world

import dev.wizrad.solarfare.game.world.core.Entity
import dev.wizrad.solarfare.generation.PlanetNode

class Planet(
  node: PlanetNode, parent: Entity): Spheroid<PlanetNode>(node, parent) {

}
