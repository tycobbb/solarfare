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
  config: Config,
  private val shipFactory:   Provider<ShipNode>,
  private val systemFactory: Provider<SolarSystemNode>,
  strategy: ClusteringStrategy): Node("space") {

  // MARK: Properties
  private val model   = config.space
  private val cluster = Cluster(strategy)

  /** The unit size of the corresponding materializable */
  var size = Size.zero

  // MARK: Children
  lateinit var ship:    ShipNode private set
  lateinit var systems: Iterable<SolarSystemNode> private set

  // MARK: Lifecycle
  override fun generate() {
    super.generate()

    // generate solar systems
    systems = cluster.add(
      child(systemFactory)
        .decay(from = 1.0) { it * 0.05 }
        .generate())

    // generate ship
    ship = child(shipFactory)
      .generate()
  }

  override fun didGenerate() {
    super.didGenerate()

    // position systems
    cluster.resolve(dissipation = 10.0)

    // size space based on radius of cluster
    size = Size(cluster.radius())

    // position ship inside space
    ship.center = Point(
      rand().upto(size.width)  - size.width  / 2,
      rand().upto(size.height) - size.height / 2
    )
  }
}