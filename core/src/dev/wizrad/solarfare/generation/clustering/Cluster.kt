package dev.wizrad.solarfare.generation.clustering

class Cluster(
  private val strategy: ClusteringStrategy) {

  // MARK: Properties
  private val clusterables = mutableListOf<Clusterable>()

  // MARK: Operations
  fun <T: Clusterable> add(other: T): T {
    clusterables.add(other)
    return other
  }

  fun <T: Clusterable> add(others: Iterable<T>): Iterable<T> {
    clusterables.addAll(others)
    return others
  }

  fun resolve(dissipation: Double) {
    strategy.resolve(clusterables, dissipation)
  }

  // MARK: Accessors
  private val last: Clusterable? get() = clusterables.lastOrNull()

  fun radius(): Double {
    return last?.center?.magnitude() ?: 0.0
  }
}
