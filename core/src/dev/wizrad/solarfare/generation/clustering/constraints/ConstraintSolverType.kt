package dev.wizrad.solarfare.generation.clustering.constraints

interface ConstraintSolverType {
  fun solve(constraints: List<Constraint>)
}
