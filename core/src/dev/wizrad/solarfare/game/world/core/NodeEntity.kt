package dev.wizrad.solarfare.game.world.core

import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.generation.core.Node

abstract class NodeEntity<N: Node>(
  node: N, parent: Entity?): Entity(parent) {

}
