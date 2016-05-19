package dev.wizrad.solarfare.generation.clustering

interface ClusteringStrategy {
  /** Updates the positions of the clusterables */
  fun resolve(clusterables: List<Clusterable>, dissipation: Double)
}
