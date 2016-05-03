package dev.wizrad.solarfare.game.world

import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.game.minimap.Minimap
import dev.wizrad.solarfare.generation.PlanetNode

class Planet(
  node:    PlanetNode,
  parent:  Entity,
  minimap: Minimap): Spheroid<PlanetNode>(node, parent, minimap) {

}
