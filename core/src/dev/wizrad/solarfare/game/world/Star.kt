package dev.wizrad.solarfare.game.world

import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.game.minimap.Minimap
import dev.wizrad.solarfare.generation.StarNode

class Star(
  node:    StarNode,
  parent:  Entity,
  minimap: Minimap): Spheroid<StarNode>(node, parent, minimap) {

}
