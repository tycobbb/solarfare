package dev.wizrad.solarfare.generation

import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.generation.core.Node
import javax.inject.Inject

class SolarSystemNode @Inject constructor(
  config: Config): Node() {

  //
  // Properties
  val model = config.solarSystem
}
