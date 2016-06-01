package dev.wizrad.solarfare.generation

import dev.wizrad.solarfare.config.Spheroid
import dev.wizrad.solarfare.generation.clustering.Clusterable
import dev.wizrad.solarfare.generation.core.Node
import dev.wizrad.solarfare.support.geometry.Point

abstract class SpheroidNode(
  protected val model: Spheroid,
  tag: String): Node(tag), Clusterable {

  // MARK: Properties
  /** The name of the sprite to materialize */
  val sprite: String = model.sprite.sample()

  // MARK: Geometry
  /** The unit mass for the materialized spheroid */
  val mass: Double = model.mass.sample()
  /** The unit radius for the materialized spheroid */
  override val radius: Double = model.radius.sample()
  /** The unit position of this node, relative to its parent */
  override var center = Point.zero
}
