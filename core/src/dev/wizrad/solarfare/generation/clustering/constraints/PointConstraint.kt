package dev.wizrad.solarfare.generation.clustering.constraints

import dev.wizrad.solarfare.support.Tag
import dev.wizrad.solarfare.support.debug
import dev.wizrad.solarfare.support.geometry.Point

/** A fixed-position constraint for a single constrainable */
data class PointConstraint(
  /** The constrainable to constrain */
  val constrainable: Constrainable,
  /** The value to constrain the center to */
  val point: Point): Constraint {

  // MARK: Constraint
  override fun apply() {
    constrainable.center = point
    debug(Tag.CLUSTERING, "$this applied")
  }

  override fun toString(): String {
    return "[constraint: ${constrainable.name} pt=$point]"
  }
}
