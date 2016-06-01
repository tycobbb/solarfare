package dev.wizrad.solarfare.generation.clustering

interface ClusteringStrategy {
  /** Updates the positions of the clusterables */
  fun resolve(nodes: List<Clusterable>, dissipation: Double)
}
