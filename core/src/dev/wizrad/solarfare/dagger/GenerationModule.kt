package dev.wizrad.solarfare.dagger

import dagger.Module
import dagger.Provides
import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.generation.PlanetNode
import dev.wizrad.solarfare.generation.SolarSystemNode
import dev.wizrad.solarfare.generation.SpaceNode
import dev.wizrad.solarfare.generation.StarNode
import javax.inject.Provider

@Module
class GenerationModule {
  @Provides
  fun space(config: Config, solarSystems: Provider<SolarSystemNode>): SpaceNode {
    return SpaceNode(config, solarSystems)
  }

  @Provides
  fun solarSystem(config: Config, stars: Provider<StarNode>, planets: Provider<PlanetNode>): SolarSystemNode {
    return SolarSystemNode(config, stars, planets)
  }

  @Provides
  fun star(config: Config): StarNode {
    return StarNode(config)
  }

  @Provides
  fun planet(config: Config): PlanetNode {
    return PlanetNode(config)
  }
}
