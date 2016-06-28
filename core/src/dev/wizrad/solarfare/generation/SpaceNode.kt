package dev.wizrad.solarfare.generation

import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.generation.clustering.Cluster
import dev.wizrad.solarfare.generation.clustering.ClusteringStrategy
import dev.wizrad.solarfare.generation.core.Node
import dev.wizrad.solarfare.generation.core.child
import dev.wizrad.solarfare.generation.core.decay
import dev.wizrad.solarfare.generation.core.generate
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
    val dissipation = model.dissipation.sample()
    cluster.add(systems).resolve(dissipation)

    // calculate cluster bounds
    val bounds = cluster.bounds()

    // reposition systems based on bounds center
    val offset = bounds.offset()
    for(system in systems) {
      system.center += offset
    }

    // calculate size, position space / ship accordingly
    this.size   = bounds.size()
    ship.center = this.size.sample()
  }
}