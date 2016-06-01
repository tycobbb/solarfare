package dev.wizrad.solarfare.generation.clustering

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
    return last?.center?.magnitude() ?: 0.0
  }

  fun bounds(): Size {
    return Size.zero
  }

  private val last: Clusterable? get() {
    return clusterables.lastOrNull()
  }
}
