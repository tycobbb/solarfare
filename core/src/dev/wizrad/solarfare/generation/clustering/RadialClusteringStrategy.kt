package dev.wizrad.solarfare.generation.clustering

import dev.wizrad.solarfare.support.Maths
import dev.wizrad.solarfare.support.Tag
import dev.wizrad.solarfare.support.debug
import dev.wizrad.solarfare.support.extensions.append
import dev.wizrad.solarfare.support.extensions.between
import dev.wizrad.solarfare.support.extensions.rand
import dev.wizrad.solarfare.support.extensions.upto
import dev.wizrad.solarfare.support.fmt
import dev.wizrad.solarfare.support.geometry.Polar

class RadialClusteringStrategy: ClusteringStrategy {
  // MARK: ClusteringStrategy
  override fun resolve(clusterables: List<Clusterable>, dissipation: Double) {
    !clusterables.isEmpty() || return

    // generate monotonically increasing radii
    val radii = clusterables
      .zip(clusterables.drop(1)) // for each pair of nodes
      .fold(mutableListOf(0.0)) { radii, pair ->
        radii.append(radii.last() + deltaFor(pair, dissipation))
      }

    // update the positions of each clusterable based on the radii
    for(pair in clusterables.zip(radii)) {
      val polar = Polar(
        radial = pair.second,
        angle  = rand().upto(Maths.M_2PI))

      pair.first.center = polar.toPoint()

      // debug logging
      debug(Tag.CLUSTERING, "$this ${pair.first} @ ${pair.first.center}, rad=${polar.radial.fmt()}")
    }

    debug(Tag.CLUSTERING, "$this finished")
  }

  private fun deltaFor(pair: Pair<Clusterable, Clusterable>, dissipation: Double): Double {
    val base  = pair.first.radius + pair.second.radius
    val delta = rand().between(0.1, dissipation)

    return base + delta
  }

  // MARK: Debugging
  override fun toString(): String {
    return "[strategy.radial]"
  }
}