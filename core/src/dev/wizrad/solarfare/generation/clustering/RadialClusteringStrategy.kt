package dev.wizrad.solarfare.generation.clustering

import dev.wizrad.solarfare.support.Maths
import dev.wizrad.solarfare.support.Tag
import dev.wizrad.solarfare.support.debug
import dev.wizrad.solarfare.support.extensions.append
import dev.wizrad.solarfare.support.extensions.rand
import dev.wizrad.solarfare.support.extensions.upto
import dev.wizrad.solarfare.support.fmt
import dev.wizrad.solarfare.support.geometry.Polar

class RadialClusteringStrategy: ClusteringStrategy {
  // MARK: ClusteringStrategy
  override fun resolve(clusterables: List<Clusterable>, dissipation: Double) {
    !clusterables.isEmpty() || return

    // generate monotonically increasing radii for the clusterables
    val radii = clusterables
      .drop(1)
      .fold(mutableListOf(0.0)) { radii, clusterable ->
        radii.append(radii.last() + rand().upto(dissipation))
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

  // MARK: Debugging
  override fun toString(): String {
    return "[strategy.radial]"
  }
}