package dev.wizrad.solarfare.game.world

import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.game.ui.Minimap
import dev.wizrad.solarfare.game.world.core.NodeEntity
import dev.wizrad.solarfare.generation.SpheroidNode
import dev.wizrad.solarfare.support.Tag.WORLD
import dev.wizrad.solarfare.support.debug

abstract class Spheroid<N: SpheroidNode>(
  node:    N,
  parent:  Entity,
  minimap: Minimap): NodeEntity<N>(node, parent, minimap) {

  // MARK: Geometry
  var radius = node.radius.toFloat()

  // MARK: Lifecycle
  init {
    // geometry
    center.set(transform(node.center))

    // logging
    debug(WORLD, "spheroid center -> $center, radius -> $radius")
  }
}