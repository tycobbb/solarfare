package dev.wizrad.solarfare.generation.clustering

import dev.wizrad.solarfare.support.extensions.max
import dev.wizrad.solarfare.support.extensions.min
import dev.wizrad.solarfare.support.geometry.Bounds
import dev.wizrad.solarfare.support.geometry.Point

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

  fun bounds(): Bounds {
    return clusterables.fold(Bounds(Point.max, Point.min)) { memo, node ->
      val min = Point(
        x = min(memo.minimum.x, node.center.x - node.radius),
        y = min(memo.minimum.y, node.center.y - node.radius))

      val max = Point(
        x = max(memo.maximum.x, node.center.x + node.radius),
        y = max(memo.maximum.y, node.center.y + node.radius))

      Bounds(min, max)
    }
  }
}
