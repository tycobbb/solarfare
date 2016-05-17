package dev.wizrad.solarfare.generation.clustering.constraints

import dev.wizrad.solarfare.support.Tag
import dev.wizrad.solarfare.support.debug
import dev.wizrad.solarfare.support.fmt

/** A fixed-distance constraint between two constrainable poles */
data class StickConstraint(
  /** The value of the constraint */
  val strength: Double,
  /** The first pole for this constraint */
  val left: Constrainable,
  /** The second pole for this constraint */
  val right: Constrainable): Constraint {

  // MARK: Constraint
  override fun apply() {
    // get the delta vector / current distance between the poles
    val delta    = right.center - left.center
    val distance = delta.magnitude()

    // if the distance is non-zero, update the positions of the poles
    if(distance != 0.0) {
      // compute the percent delta relative to the strength
      val percent = (distance - strength) / distance

      // move each pole by half the percent delta to resolve the constraint
      val shift = delta * percent * 0.5
      left.center  += shift
      right.center -= shift
    }

    // debug info
    debug(Tag.CLUSTERING, "$this applied ${distance.fmt()} -> ${value.fmt()}")
  }

  // MARK: Debugging
  override fun toString(): String {
    return "[stick: ${left.name}::${right.name} str=${strength.fmt()}]"
  }

  private val value: Double
    get() = right.center.distanceTo(left.center)
}
