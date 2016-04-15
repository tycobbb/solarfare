package dev.wizrad.solarfare.generation

import dev.wizrad.solarfare.config.Spheroid
import dev.wizrad.solarfare.generation.core.Node
import dev.wizrad.solarfare.support.geometry.Point

abstract class SpheroidNode(tag: String): Node(tag) {
  //
  // Properties
  abstract protected val model: Spheroid

  /** The name of the sprite to materialize */
  lateinit var sprite: String
  /** The unit position of this node, relative to its parent */
  lateinit var center: Point
  /** The unit radius for the materialized spheroid */
  var radius: Double = 0.0
  /** The unit mass for the materialized spheroid */
  var mass: Double = 0.0

  //
  // Lifecycle
  override fun generate() {
    sprite = model.sprite.sample()
    mass   = model.mass.sample()
    radius = model.radius.sample()

    super.generate()
  }
}