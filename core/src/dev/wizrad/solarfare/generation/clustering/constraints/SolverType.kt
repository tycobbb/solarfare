package dev.wizrad.solarfare.generation.clustering.constraints

interface SolverType {
  fun solve(constraints: List<Constraint>)
}
