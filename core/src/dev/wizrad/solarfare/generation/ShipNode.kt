package dev.wizrad.solarfare.generation

import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.generation.core.Node
import dev.wizrad.solarfare.support.geometry.Point
import javax.inject.Inject

class ShipNode @Inject constructor(
  config: Config): Node("ship") {

  // MARK: Properties
  private val model = config.ship
  /** The unit position of this node, relative to its parent */
  var center = Point.zero
}