package dev.wizrad.solarfare.generation.clustering

import dev.wizrad.solarfare.generation.clustering.constraints.Constraint
import dev.wizrad.solarfare.generation.clustering.constraints.ConstraintSolverType
import dev.wizrad.solarfare.generation.clustering.constraints.DistanceConstraint
import dev.wizrad.solarfare.support.Tag
import dev.wizrad.solarfare.support.debug
import dev.wizrad.solarfare.support.extensions.between
import dev.wizrad.solarfare.support.extensions.flatMapIndexed
import dev.wizrad.solarfare.support.extensions.rand
import javax.inject.Inject

class PlanarClusteringStrategy @Inject constructor(
  private val solver: ConstraintSolverType): ClusteringStrategy {

  // MARK: ClusteringStrategy
  override fun resolve(clusterables: List<Clusterable>, dissipation: Double) {
    !clusterables.isEmpty() || return

    // generate a constraint between each pair of clusterables
    val constraints = clusterables
      .flatMapIndexed { i, left ->
        val strength = rand().between(0.1, dissipation)
        clusterables.mapIndexedNotNull { j, right ->
          if(i != j) DistanceConstraint(strength, left, right) else null
        }
      }

    // solve the constraints to position the clusterables
    solver.solve(constraints)

    // log final positions of clusterables
    debug(Tag.CLUSTERING, "$this finished ${describe(clusterables, constraints)}")
  }

  // MARK: Debugging
  override fun toString(): String {
    return "[strategy.planar]"
  }

  private fun describe(clusterables: List<Clusterable>, constraints: List<Constraint>): String {
    return clusterables.fold("") { memo, clusterable ->
      memo + "\n\t$clusterable ${clusterable.center}"
    }
  }
}
