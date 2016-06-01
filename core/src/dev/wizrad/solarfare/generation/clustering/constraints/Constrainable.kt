package dev.wizrad.solarfare.generation.clustering.constraints

import dev.wizrad.solarfare.support.geometry.Point

interface Constrainable {
  /** @property name An identifier for the constrainable to aid in logging/debugging */
  val name: String
  /** @property center The current position of the constrainable */
  var center: Point
}
