package dev.wizrad.solarfare.generation.clustering

class RadialClusteringStrategy: ClusteringStrategy {
  // MARK: Properties
  private val maximumDistance = 10.0

  // MARK: ClusteringStrategy
  override fun resolve(clusterables: List<Clusterable>) {
    !clusterables.isEmpty() || return
  }

  // MARK: Debugging
  override fun toString(): String {
    return "[strategy.radial]"
  }
}