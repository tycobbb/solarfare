package dev.wizrad.solarfare.generation

import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.generation.clustering.Cluster
import dev.wizrad.solarfare.generation.clustering.ClusteringStrategy
import dev.wizrad.solarfare.generation.core.Node
import dev.wizrad.solarfare.generation.core.child
import dev.wizrad.solarfare.generation.core.decay
import dev.wizrad.solarfare.generation.core.generate
import dev.wizrad.solarfare.support.extensions.rand
import dev.wizrad.solarfare.support.extensions.upto
import dev.wizrad.solarfare.support.geometry.Point
import dev.wizrad.solarfare.support.geometry.Size
import javax.inject.Inject
import javax.inject.Provider

class SpaceNode @Inject constructor(
  config:        Config,
  shipFactory:   Provider<ShipNode>,
  systemFactory: Provider<SolarSystemNode>,
  strategy:      ClusteringStrategy): Node("space") {

  // MARK: Properties
  private val model   = config.space
  private val cluster = Cluster(strategy)

  // MARK: Geometry
  /** The unit size of the corresponding materializable */
  var size = Size.zero

  // MARK: Children
  val ship:    ShipNode
  val systems: Iterable<SolarSystemNode>

  // MARK: Lifecycle
  init {
    ship = child(shipFactory)
      .generate()

    systems = child(systemFactory)
      .decay(from = 1.0) { it * 0.05 }
      .generate()
  }

  override fun didGenerate() {
    super.didGenerate()

    // position systems
    cluster.add(systems)
      .resolve(model.dissipation.sample())

    // size space based on radius of cluster
    size = Size(cluster.radius())

    // position ship inside space
    ship.center = Point(
      rand().upto(size.width)  - size.width  / 2,
      rand().upto(size.height) - size.height / 2
    )
  }
}