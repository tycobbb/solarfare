package dev.wizrad.solarfare.generation

import dev.wizrad.solarfare.config.Config
import javax.inject.Inject

class PlanetNode @Inject constructor(
  config: Config): SpheroidNode(config.planet, "planet") {

}
