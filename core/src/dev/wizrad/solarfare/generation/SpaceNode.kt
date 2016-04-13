package dev.wizrad.solarfare.generation

import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.extensions.between
import dev.wizrad.solarfare.extensions.rand
import dev.wizrad.solarfare.extensions.upto
import dev.wizrad.solarfare.generation.core.Node
import dev.wizrad.solarfare.generation.core.Spec
import dev.wizrad.solarfare.support.geometry.Point
import dev.wizrad.solarfare.support.geometry.Size
import javax.inject.Inject
import javax.inject.Provider

class SpaceNode @Inject constructor(
  config:           Config,
  val ships:        Provider<ShipNode>,
  val solarSystems: Provider<SolarSystemNode>): Node("space") {

  //
  // Properties
  val model = config.space

  /** The unit size of the corresponding materializable */
  lateinit var size: Size

  //
  // Lifecycle
  init {
    resource = "space"
  }

  override fun generate() {
    size = Size(model.size.sample())
    super.generate()
  }

  private fun generated(node: ShipNode) {
    node.center = Point(
      rand().upto(size.width),
      rand().upto(size.height)
    )
  }

  private fun generated(node: SolarSystemNode) {
    node.center = Point(
      rand().between(node.radius, size.width  - node.radius),
      rand().between(node.radius, size.height - node.radius)
    )
  }

  //
  // Spec
  override fun spec(): Spec.Builder {
    val spec = super.spec()

    spec.child { ships.get() }
      .require(1)
      .afterGenerate { generated(it) }

    spec.child { solarSystems.get() }
      .weight(1000).decay { it * 5 }
      .afterGenerate { generated(it) }

    return spec
  }
}