package dev.wizrad.solarfare.game.world

import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.generation.StarNode

class Star(
  node:    StarNode,
  parent:  Entity,
  world:   World): Spheroid<StarNode>(node, parent, world) {

  init {
    // minimap
    trackOn(world.minimap)
  }
}
