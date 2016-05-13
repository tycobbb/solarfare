package dev.wizrad.solarfare.generation.clustering.constraints

import dev.wizrad.solarfare.support.Tag
import dev.wizrad.solarfare.support.debug
import dev.wizrad.solarfare.support.extensions.repeat
import dev.wizrad.solarfare.support.geometry.Point

class Solver(
  private val iterations: Int = 2): SolverType {

  // MARK: SolverType

  /**
   Solves the current set of constraints by Verlet integration and constraint relaxation.
   See: http://www.gamasutra.com/resource_guide/20030121/jacobson_pfv.htm
  */
  override fun solve(constraints: List<Constraint>) {
    // start from randomized positions
    randomizePositions(constraints)

    // make an arbitrary number of passes through the constraint relaxation to get close
    // enough to the constrained values
    iterations.repeat {
      for(constraint in constraints) {
        apply(constraint)
      }
    }

    debug(Tag.CLUSTERING, "solver did finish")
  }

  private fun randomizePositions(constraints: List<Constraint>) {
    // find the constraint with the highest strength
    val maximum = constraints.maxBy { it.strength }
    maximum ?: return

    for(constraint in constraints) {
      randomizePosition(constraint.left,  maximum.strength)
      randomizePosition(constraint.right, maximum.strength)
    }
  }

  private fun randomizePosition(constrainable: Constrainable, maximum: Double) {
    // only update the position if its currently zero
    if(constrainable.position.isZero()) {
      constrainable.position = Point.random(maximum)
    }
  }

  private fun apply(constraint: Constraint) {
    // get the delta vector / current distance between the poles
    val delta    = constraint.right.position - constraint.left.position
    val distance = delta.magnitude()

    // TODO: how to handle 0-valued distances / strengths?
    // compute the percent distance from the strength, if possible
    val percent = (distance - constraint.strength) / distance

    // move each pole by half the percent delta to resolve the constraint
    val shift = delta * percent * 0.5
    constraint.left.position  -= shift
    constraint.right.position += shift

    // debug info
    debug(Tag.CLUSTERING, "relaxed constraint " +
      "l -> ${description(constraint.left)} r -> ${description(constraint.right)}")
  }

  private fun description(constrainable: Constrainable): String {
    return "${constrainable.name}.${constrainable.position}"
  }
}
