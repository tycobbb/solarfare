package dev.wizrad.solarfare.generation.clustering

import dev.wizrad.solarfare.generation.clustering.constraints.Constraint
import dev.wizrad.solarfare.generation.clustering.constraints.PointConstraint
import dev.wizrad.solarfare.generation.clustering.constraints.SolverType
import dev.wizrad.solarfare.generation.clustering.constraints.StickConstraint
import dev.wizrad.solarfare.support.Tag
import dev.wizrad.solarfare.support.debug
import dev.wizrad.solarfare.support.extensions.between
import dev.wizrad.solarfare.support.extensions.rand
import dev.wizrad.solarfare.support.fmt
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

    // log final positions of clusterables
    debug(Tag.CLUSTERING, "$this finished ${describe(clusterables, constraints)}")
  }

  private fun randomizePositions(clusterables: List<Clusterable>, maximum: Double) {
    // clamp root clusterable to zero
    clusterables.first().center = Point.zero

    // randomize everything else along the x-axis
    for(clusterable in clusterables.drop(1)) {
      clusterable.center = Point.random(maximum, 0.0)
    }
  }

  // MARK: Debugging
  override fun toString(): String {
    return "[strategy.radial]"
  }

  private fun describe(clusterables: List<Clusterable>, constraints: List<Constraint>): String {
    return clusterables
      .drop(1).zip(constraints)
      .fold("") { memo, pair -> memo + "\n\t${describe(pair.first)} ${pair.second}" }
  }

  private fun describe(clusterable: Clusterable): String {
    return "${clusterable.name}->${clusterable.center.x.fmt()}"
  }
}