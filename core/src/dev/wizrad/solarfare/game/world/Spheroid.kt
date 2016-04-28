package dev.wizrad.solarfare.game.world

import dev.wizrad.solarfare.game.renderer.support.set
import dev.wizrad.solarfare.game.world.core.Entity
import dev.wizrad.solarfare.generation.SpheroidNode
import dev.wizrad.solarfare.support.Tag.WORLD
import dev.wizrad.solarfare.support.debug

abstract class Spheroid<N: SpheroidNode>(
  node: N): Entity<N>(node) {

  // MARK: Geometry
  var radius = node.radius.toFloat()

  init {
    center.set(node.center)
  }

  init {
    debug(WORLD, "spheroid center -> $center, radius -> $radius")
  }
}