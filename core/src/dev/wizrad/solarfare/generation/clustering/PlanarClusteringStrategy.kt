package dev.wizrad.solarfare.generation.clustering

import dev.wizrad.solarfare.generation.clustering.constraints.Constraint
import dev.wizrad.solarfare.generation.clustering.constraints.ConstraintSolverType
import dev.wizrad.solarfare.generation.clustering.constraints.DistanceConstraint
import dev.wizrad.solarfare.support.Tag
import dev.wizrad.solarfare.support.debug
import dev.wizrad.solarfare.support.extensions.flatMapIndexed
import dev.wizrad.solarfare.support.extensions.rand
import dev.wizrad.solarfare.support.geometry.Point
import javax.inject.Inject

class PlanarClusteringStrategy @Inject constructor(
  private val solver: ConstraintSolverType): ClusteringStrategy {

  // MARK: ClusteringStrategy
  override fun resolve(nodes: List<Clusterable>, dissipation: Double) {
    !nodes.isEmpty() || return

    debug(Tag.Clustering, "$this started")

    // choose non-zero starting positions so that distance constraints can calculate
    // real position deltas
    randomizePositionsOf(nodes, dissipation)

    // generate a distance constraint between each pair of nodes
    val constraints = nodes
      .flatMapIndexed { i, left ->
        nodes.mapIndexedNotNull { j, right ->
          if(i != j) constraintFor(left, right, dissipation) else null
        }
      }

    // solve the constraints to position the nodes
    solver.solve(constraints)

    // log final positions of nodes
    debug(Tag.Clustering, "$this finished ${describe(nodes, constraints)}")
  }

  // MARK: Helpers
  private fun randomizePositionsOf(nodes: List<Clusterable>, dissipation: Double) {
    // scale down the dissipation by some arbitrary value so that nodes remain close
    val range = (0.01 * dissipation)..(dissipation * 0.1)

    for(node in nodes) {
      node.center = Point.random(range)
    }
  }

  private fun constraintFor(left: Clusterable, right: Clusterable, dissipation: Double): DistanceConstraint {
    val base  = left.radius + right.radius
    val delta = rand.between(0.1, dissipation)

    return DistanceConstraint(base + delta, left, right)
  }

  // MARK: Debugging
  override fun toString(): String {
    return "[strategy.planar]"
  }

  private fun describe(nodes: List<Clusterable>, constraints: List<Constraint>): String {
    return nodes.fold("") { memo, node ->
      memo + "\n\t$node ${node.center}"
    }
  }
}
