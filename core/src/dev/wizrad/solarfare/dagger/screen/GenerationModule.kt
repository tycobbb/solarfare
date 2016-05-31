package dev.wizrad.solarfare.dagger.screen

import dagger.Module
import dagger.Provides
import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.generation.*
import dev.wizrad.solarfare.generation.clustering.PlanarClusteringStrategy
import dev.wizrad.solarfare.generation.clustering.RadialClusteringStrategy
import dev.wizrad.solarfare.generation.clustering.constraints.ConstraintSolver
import dev.wizrad.solarfare.generation.clustering.constraints.ConstraintSolverType
import dev.wizrad.solarfare.generation.core.Root
import javax.inject.Provider

@Module
class GenerationModule {
  // MARK: Nodes
  @Provides
  fun root(node: SpaceNode): Root<SpaceNode> {
    return Root(node)
  }

  @Provides
  fun space(
    config:   Config,
    ships:    Provider<ShipNode>,
    systems:  Provider<SolarSystemNode>,
    strategy: PlanarClusteringStrategy): SpaceNode {

    return SpaceNode(config, ships, systems, strategy)
  }

  @Provides
  fun ship(config: Config): ShipNode {
    return ShipNode(config)
  }

  @Provides
  fun solarSystem(
    config:   Config,
    stars:    Provider<StarNode>,
    planets:  Provider<PlanetNode>,
    strategy: RadialClusteringStrategy): SolarSystemNode {

    return SolarSystemNode(config, stars, planets, strategy)
  }

  @Provides
  fun star(config: Config): StarNode {
    return StarNode(config)
  }

  @Provides
  fun planet(config: Config): PlanetNode {
    return PlanetNode(config)
  }

  // MARK: Clustering
  @Provides
  fun radialStrategy(): RadialClusteringStrategy {
    return RadialClusteringStrategy()
  }

  @Provides
  fun planarStrategy(solver: ConstraintSolverType): PlanarClusteringStrategy {
    return PlanarClusteringStrategy(solver)
  }

  @Provides
  fun solver(): ConstraintSolverType {
    return ConstraintSolver()
  }
}
