package dev.wizrad.solarfare.dagger

import dagger.Module
import dagger.Provides
import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.generation.SolarSystemNode
import dev.wizrad.solarfare.generation.SpaceNode
import javax.inject.Provider

@Module
class GenerationModule {
  @Provides
  fun space(config: Config, solarSystems: Provider<SolarSystemNode>): SpaceNode {
    return SpaceNode(config, solarSystems)
  }

  @Provides
  fun solarSystem(config: Config): SolarSystemNode {
    return SolarSystemNode(config)
  }
}
