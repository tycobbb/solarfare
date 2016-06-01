package dev.wizrad.solarfare.generation

import dev.wizrad.solarfare.config.Config
import javax.inject.Inject

class StarNode @Inject constructor(
  config: Config): SpheroidNode(config.star, "star") {

}
