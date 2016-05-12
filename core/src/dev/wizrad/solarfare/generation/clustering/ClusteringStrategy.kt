package dev.wizrad.solarfare.generation.clustering

interface ClusteringStrategy {
  /** Clusters the parameterized `clusterables` */
  fun resolve(clusterables: List<Clusterable>)
}
