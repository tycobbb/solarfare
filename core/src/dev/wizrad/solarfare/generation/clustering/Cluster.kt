package dev.wizrad.solarfare.generation.clustering

class Cluster(
  private val strategy: ClusteringStrategy) {

  // MARK: Properties
  private val clusterables = mutableListOf<Clusterable>()

  // MARK: Operations
  fun add(clusterable: Clusterable) {
    clusterables.add(clusterable)
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
