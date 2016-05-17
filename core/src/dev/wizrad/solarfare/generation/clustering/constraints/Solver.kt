package dev.wizrad.solarfare.generation.clustering.constraints

import dev.wizrad.solarfare.support.Tag
import dev.wizrad.solarfare.support.debug
import dev.wizrad.solarfare.support.extensions.repeat

class Solver(
  private val iterations: Int = 2): SolverType {

  /**
   Solves the set of constraints through iterative constraint relaxation
   See: http://www.gamasutra.com/resource_guide/20030121/jacobson_pfv.htm
  */

  override fun solve(constraints: List<Constraint>) {
    debug(Tag.CLUSTERING, "$this start")

    // make an arbitrary number of passes through the constraint relaxation to get close
    // enough to the constrained values
    iterations.repeat {
      for(constraint in constraints) {
        constraint.apply()
      }
    }

    debug(Tag.CLUSTERING, "$this finished")
  }

  // MARK: Debugging
  override fun toString(): String {
    return "[solver]"
  }
}
