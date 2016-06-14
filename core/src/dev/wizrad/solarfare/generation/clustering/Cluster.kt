package dev.wizrad.solarfare.generation.clustering

import dev.wizrad.solarfare.support.extensions.max
import dev.wizrad.solarfare.support.extensions.min
import dev.wizrad.solarfare.support.geometry.Point
import dev.wizrad.solarfare.support.geometry.Size

class Cluster(
  private val strategy: ClusteringStrategy) {

  // MARK: Properties
  private val clusterables = mutableListOf<Clusterable>()

  // MARK: Operations
  fun <T: Clusterable> add(other: T): Cluster {
    clusterables.add(other)
    return this
  }

  fun <T: Clusterable> add(others: Iterable<T>): Cluster {
    clusterables.addAll(others)
    return this
  }

  fun resolve(dissipation: Double) {
    strategy.resolve(clusterables, dissipation)
  }

  // MARK: Extent Calculation
  fun radius(): Double {
    val last = clusterables.lastOrNull()
    return if (last != null) last.center.magnitude() + last.radius else 0.0
  }

  fun bounds(): Size {
    val extents = extents()
    val result  = Size(
      width  = extents.second.x - extents.first.x,
      height = extents.second.y - extents.first.y)

    return result
  }

  private fun extents(): Pair<Point, Point> {
    return clusterables.fold(Pair(Point.zero, Point.zero)) { memo, node ->
      val min = Point(
        x = min(memo.first.x, node.center.x - node.radius),
        y = min(memo.first.y, node.center.y - node.radius))

      val max = Point(
        x = max(memo.first.x, node.center.x + node.radius),
        y = max(memo.first.y, node.center.y + node.radius))

      Pair(min, max)
    }
  }

  private val last: Clusterable? get() {
    return clusterables.lastOrNull()
  }
}
