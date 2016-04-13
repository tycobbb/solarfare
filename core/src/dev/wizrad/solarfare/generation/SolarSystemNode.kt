package dev.wizrad.solarfare.generation

import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.generation.core.Node
import dev.wizrad.solarfare.generation.core.Spec
import dev.wizrad.solarfare.support.geometry.Point
import javax.inject.Inject
import javax.inject.Provider

class SolarSystemNode @Inject constructor(
  config:      Config,
  val stars:   Provider<StarNode>,
  val planets: Provider<PlanetNode>): Node("system") {

  //
  // Properties
  val model = config.solarSystem

  /** The unit position of this node, relative to its parent */
  lateinit var center: Point
  /** The unit radius of the solar system */
  var radius: Double = 0.0

  //
  // Lifecycle
  private fun generated(node: StarNode) {
    node.center = Point(0.0, 0.0)
  }

  private fun shouldGenerate(node: PlanetNode): Boolean {
    node.center = Point(0.0, 0.0)
    return true
  }

  //
  // Spec
  override fun spec(): Spec.Builder {
    val spec = super.spec()

    spec.child { stars.get() }
      .require(1)
      .afterGenerate { generated(it) }

    spec.child { planets.get() }
      .weight(80).decay { it * 5 }
      .filter { shouldGenerate(it) }

    return spec
  }
}
