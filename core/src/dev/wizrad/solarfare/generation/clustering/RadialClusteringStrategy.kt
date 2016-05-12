package dev.wizrad.solarfare.generation.clustering

import dev.wizrad.solarfare.generation.clustering.constraints.Constraint
import dev.wizrad.solarfare.generation.clustering.constraints.SolverType
import dev.wizrad.solarfare.support.extensions.between
import dev.wizrad.solarfare.support.extensions.rand
import javax.inject.Inject

class RadialClusteringStrategy @Inject constructor(
  private val solver: SolverType): ClusteringStrategy {

  // MARK: ClusteringStrategy
  override fun resolve(clusterables: List<Clusterable>) {
    // generate constraints from each consecutive pair of clusterables
    val constraints = clusterables
      .dropLast(1).zip(clusterables.drop(1))
      .map { pair ->
        val strength = rand().between(0.0, 10.0)
        Constraint(strength, pair.first, pair.second)
      }

    // solve the constraints to position the clusterables
    solver.solve(constraints)
  }
}