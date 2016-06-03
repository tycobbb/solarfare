package dev.wizrad.solarfare.game.world

import dev.wizrad.solarfare.game.core.Entity
import dev.wizrad.solarfare.game.ui.Minimap
import dev.wizrad.solarfare.game.world.core.NodeEntity
import dev.wizrad.solarfare.generation.SpheroidNode
import dev.wizrad.solarfare.support.Tag
import dev.wizrad.solarfare.support.debug
import dev.wizrad.solarfare.support.fmt

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
    debug(Tag.World, "$this created")
  }

  // MARK: Debugging
  override fun toString(): String {
    return "[spheroid center=$center rad=${radius.fmt()}]"
  }
}