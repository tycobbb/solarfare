package dev.wizrad.solarfare.generation

import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.generation.core.Node
import dev.wizrad.solarfare.generation.core.Spec
import javax.inject.Inject
import javax.inject.Provider

class SpaceNode @Inject constructor(
  config: Config,
  val solarSystems: Provider<SolarSystemNode>): Node("space") {

  //
  // Properties
  val model = config.space

  //
  // Lifecycle
  private fun generated(node: SolarSystemNode) {

  }

  //
  // Spec
  override fun spec(): Spec.Builder {
    val spec = super.spec()

    spec.child { solarSystems.get() }
      .weight(1000).decay { it * 5 }
      .afterGenerate { generated(it) }

    return spec
  }
}