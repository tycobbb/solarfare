package dev.wizrad.solarfare.generation.clustering.constraints

import dev.wizrad.solarfare.support.geometry.Point

interface Constrainable {
  /** The current position of the constrainable */
  var center: Point
  /** An identifier for the constrainable to aid in logging/debugging */
  val name: String
}
