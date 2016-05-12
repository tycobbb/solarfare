package dev.wizrad.solarfare.generation.clustering

class Cluster(
  private val strategy: ClusteringStrategy) {

  // MARK: Properties
  private val clusterables = mutableListOf<Clusterable>()

  // MARK: Operations
  fun add(clusterable: Clusterable) {
    clusterables.add(clusterable)
  }

  fun resolve() {
    strategy.resolve(clusterables)
  }
}
