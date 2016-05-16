package dev.wizrad.solarfare.generation

import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.generation.core.Node
import dev.wizrad.solarfare.generation.core.Spec
import dev.wizrad.solarfare.support.extensions.between
import dev.wizrad.solarfare.support.extensions.rand
import dev.wizrad.solarfare.support.extensions.upto
import dev.wizrad.solarfare.support.geometry.Point
import dev.wizrad.solarfare.support.geometry.Size
import javax.inject.Inject
import javax.inject.Provider

class SpaceNode @Inject constructor(
  config: Config,
  private val ships:        Provider<ShipNode>,
  private val solarSystems: Provider<SolarSystemNode>): Node("space") {

  // MARK: Properties
  private val model = config.space
  /** The unit size of the corresponding materializable */
  lateinit var size: Size

  // MARK: Lifecycle
  init {
    resource = "space"
  }

  override fun willGenerate() {
    super.willGenerate()
    size = Size(model.size.sample())
  }

  private fun generated(node: ShipNode) {
    node.center = Point(
      rand().upto(size.width)  - size.width  / 2,
      rand().upto(size.height) - size.height / 2
    )
  }

  private fun generated(node: SolarSystemNode) {
    node.center = Point(
      rand().between(node.radius, size.width  - node.radius) - size.width  / 2,
      rand().between(node.radius, size.height - node.radius) - size.height / 2
    )
  }

  // MARK: Spec
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