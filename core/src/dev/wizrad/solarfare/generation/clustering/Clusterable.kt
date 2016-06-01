package dev.wizrad.solarfare.generation.clustering

import dev.wizrad.solarfare.generation.clustering.constraints.Constrainable

interface Clusterable: Constrainable {
  /** @property radius The radius of the clusterable */
  val radius: Double
}
