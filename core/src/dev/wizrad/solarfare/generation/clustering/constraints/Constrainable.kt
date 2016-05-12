package dev.wizrad.solarfare.generation.clustering.constraints

import dev.wizrad.solarfare.support.geometry.Point

interface Constrainable {
  /** The current position of the constrainable */
  val position: Point
}
