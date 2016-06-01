package dev.wizrad.solarfare.generation.clustering.constraints

import dev.wizrad.solarfare.support.extensions.repeat

/**
 Solves the set of constraints through iterative constraint relaxation
 See: http://www.gamasutra.com/resource_guide/20030121/jacobson_pfv.htm
*/
class ConstraintSolver(
  private val iterations: Int = 4): ConstraintSolverType {

  override fun solve(constraints: List<Constraint>) {
    // make an arbitrary number of passes through the constraint relaxation to get close
    // enough to the constrained values
    iterations.repeat {
      for(constraint in constraints) {
        constraint.apply()
      }
    }
  }

  // MARK: Debugging
  override fun toString(): String {
    return "[solver]"
  }
}
