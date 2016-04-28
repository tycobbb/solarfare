package dev.wizrad.solarfare.game.world

import dev.wizrad.solarfare.game.world.core.Entity
import dev.wizrad.solarfare.generation.StarNode

class Star(
  node: StarNode, parent: Entity): Spheroid<StarNode>(node, parent) {

}
