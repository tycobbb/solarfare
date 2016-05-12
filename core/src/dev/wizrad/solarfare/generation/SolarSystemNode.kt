package dev.wizrad.solarfare.generation

import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.generation.clustering.ClusteringStrategy
import dev.wizrad.solarfare.generation.core.Node
import dev.wizrad.solarfare.generation.core.Spec
import dev.wizrad.solarfare.support.Maths
import dev.wizrad.solarfare.support.Tag
import dev.wizrad.solarfare.support.collections.Stripe
import dev.wizrad.solarfare.support.debug
import dev.wizrad.solarfare.support.extensions.center
import dev.wizrad.solarfare.support.extensions.rand
import dev.wizrad.solarfare.support.extensions.upto
import dev.wizrad.solarfare.support.geometry.Point
import dev.wizrad.solarfare.support.geometry.Polar
import javax.inject.Inject
import javax.inject.Provider

class SolarSystemNode @Inject constructor(
  config: Config,
  private val stars:    Provider<StarNode>,
  private val planets:  Provider<PlanetNode>,
  private val strategy: ClusteringStrategy): Node("system") {

  // MARK: Properties
  private val model = config.solarSystem
  private var orbitals: Stripe? = null

  /** The unit position of this node, relative to its parent */
  lateinit var center: Point
  /** The unit radius of the solar system */
  var radius: Double = 0.0

  // MARK: Lifecycle
  override fun generate() {
    radius = model.radius.sample()
    super.generate()
  }

  private fun generated(star: StarNode) {
    star.center = Point(0.0, 0.0)

    // create a stripe for inserting planets at positions that don't overlap the star
    orbitals = Stripe(star.radius..(radius - star.radius))
  }

  private fun shouldGenerate(planet: PlanetNode): Boolean {
    // find an orbital radius, or don't generate if we couldn't fit the planet
    val orbital = orbitals?.insert(planet.radius * 2.0)

    if(orbital != null) {
      // generate a random polar coordinate for this planet
      val coordinate = Polar(orbital.center, rand().upto(Maths.M_2PI))
      debug(Tag.GENERATION, "$planet orbital -> ${coordinate.radial}")

      // convert coordinate to a cartesian point
      planet.center = coordinate.toPoint()

      return true
    }

    return false
  }

  // MARK: Spec
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
