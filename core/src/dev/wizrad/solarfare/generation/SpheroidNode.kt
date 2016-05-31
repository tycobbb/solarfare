package dev.wizrad.solarfare.generation

import dev.wizrad.solarfare.config.Spheroid
import dev.wizrad.solarfare.generation.clustering.Clusterable
import dev.wizrad.solarfare.generation.core.Node
import dev.wizrad.solarfare.support.geometry.Point

abstract class SpheroidNode(
  tag: String): Node(tag), Clusterable {

  // MARK: Properties
  abstract protected val model: Spheroid

  /** The name of the sprite to materialize */
  var sprite: String = ""
  /** The unit radius for the materialized spheroid */
  var radius: Double = 0.0
  /** The unit mass for the materialized spheroid */
  var mass: Double = 0.0

  /** The unit position of this node, relative to its parent */
  override var center = Point.zero

  // MARK: Lifecycle
  override fun generate() {
    super.generate()

    sprite = model.sprite.sample()
    mass   = model.mass.sample()
    radius = model.radius.sample()
  }
}
