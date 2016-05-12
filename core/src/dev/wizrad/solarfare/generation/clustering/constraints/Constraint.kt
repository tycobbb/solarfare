package dev.wizrad.solarfare.generation.clustering.constraints

/** A fixed-width distance constraint between two constrainable poles */
data class Constraint(
  /** The value of the constraint */
  private val strength: Double,
  /** The first pole for this constraint */
  private val left: Constrainable,
  /** The second pole for this constraint */
  private val right: Constrainable) {

  // MARK: Computation
  /** Computes the current distance between the poles */
  fun value(): Double {
    return 0.0 // return (right.position - left.position).magnitude
  }

  // MARK: Debugging
  override fun toString(): String {
    return "[constraint: strength = $strength, value = ${value()}]"
  }
}
