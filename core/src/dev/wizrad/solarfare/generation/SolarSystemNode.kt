package dev.wizrad.solarfare.generation

import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.generation.clustering.Cluster
import dev.wizrad.solarfare.generation.clustering.Clusterable
import dev.wizrad.solarfare.generation.clustering.ClusteringStrategy
import dev.wizrad.solarfare.generation.core.Node
import dev.wizrad.solarfare.generation.core.child
import dev.wizrad.solarfare.generation.core.decay
import dev.wizrad.solarfare.generation.core.generate
import dev.wizrad.solarfare.support.geometry.Point
import javax.inject.Inject
import javax.inject.Provider

class SolarSystemNode @Inject constructor(
  config: Config,
  private val starFactory:   Provider<StarNode>,
  private val planetFactory: Provider<PlanetNode>,
  strategy: ClusteringStrategy): Node("system"), Clusterable {

  // MARK: Properties
  private val model   = config.solarSystem
  private val cluster = Cluster(strategy)

  /** The unit radius of the solar system */
  var radius: Double = 0.0
  /** The unit position of this node, relative to its parent */
  override var center = Point.zero

  // MARK: Children
  lateinit var star:    StarNode private set
  lateinit var planets: Iterable<PlanetNode> private set

  // MARK: Lifecycle
  override fun generate() {
    super.generate()

    // generate / cluster stars
    star = cluster.add(
      child(starFactory)
        .generate())

    // generate / cluster planets
    planets = cluster.add(
      child(planetFactory)
        .decay(from = 1.0) { it * 0.05 }
        .generate())
  }

  override fun didGenerate() {
    super.didGenerate()

    // position systems
    cluster.resolve(model.dissipation.sample())

    // size solar system based on extent of the cluster
    radius = cluster.radius()
  }
}
