package dev.wizrad.solarfare.generation.clustering

import dev.wizrad.solarfare.generation.clustering.constraints.Constraint
import dev.wizrad.solarfare.generation.clustering.constraints.PointConstraint
import dev.wizrad.solarfare.generation.clustering.constraints.SolverType
import dev.wizrad.solarfare.generation.clustering.constraints.StickConstraint
import dev.wizrad.solarfare.support.extensions.between
import dev.wizrad.solarfare.support.extensions.rand
import dev.wizrad.solarfare.support.geometry.Point
import javax.inject.Inject

class RadialClusteringStrategy @Inject constructor(
  private val solver: SolverType): ClusteringStrategy {

  // MARK: Properties
  private val maximumDistance = 10.0

  // MARK: ClusteringStrategy
  override fun resolve(clusterables: List<Clusterable>) {
    !clusterables.isEmpty() || return

    // randomize the positions of the clusterables
    randomizePositions(clusterables, maximumDistance)

    // generate the list of constraints
    val constraints = mutableListOf<Constraint>()

    // add a stick constraint for each pair of clusterables
    constraints.addAll(clusterables
      .dropLast(1).zip(clusterables.drop(1))
      .map { pair ->
        val strength = rand().between(0.1, maximumDistance)
        StickConstraint(strength, pair.first, pair.second)
      })

    // fix the position of the first clusterable
    constraints.add(PointConstraint(clusterables.first(), Point.zero))

    // solve the constraints to position the clusterables
    solver.solve(constraints)
  }

  private fun randomizePositions(clusterables: List<Clusterable>, maximum: Double) {
    clusterables.first().center = Point.zero
    for(clusterable in clusterables.drop(1)) {
      clusterable.center = Point.random(maximum)
    }
  }
}