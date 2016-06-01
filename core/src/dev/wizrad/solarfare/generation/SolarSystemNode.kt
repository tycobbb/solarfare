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
  config:        Config,
  starFactory:   Provider<StarNode>,
  planetFactory: Provider<PlanetNode>,
  strategy:      ClusteringStrategy): Node("system"), Clusterable {

  // MARK: Properties
  private val model   = config.solarSystem
  private val cluster = Cluster(strategy)

  // MARK: Geometry
  /** The unit radius of the solar system */
  override var radius: Double = 0.0
  /** The unit position of this node, relative to its parent */
  override var center = Point.zero

  // MARK: Children
  val star:    StarNode
  val planets: Iterable<PlanetNode>

  // MARK: Lifecycle
  init {
    star = child(starFactory)
      .generate()

    planets = child(planetFactory)
      .decay(from = 1.0) { it * 0.05 }
      .generate()
  }

  override fun didGenerate() {
    super.didGenerate()

    // position systems
    cluster.add(star).add(planets)
      .resolve(model.dissipation.sample())

    // size solar system based on extent of the cluster
    radius = cluster.radius()
  }
}
