package dev.wizrad.solarfare.dagger

import dagger.Module
import dagger.Provides
import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.game.MainScreen
import dev.wizrad.solarfare.generation.SpaceNode
import dev.wizrad.solarfare.generation.core.Root

@Module
class GameModule {
  @Provides
  fun config(): Config {
    return Config.load()
  }

  @Provides
  fun mainScreen(root: Root<SpaceNode>): MainScreen {
    return MainScreen(root)
  }

  @Provides
  fun root(node: SpaceNode): Root<SpaceNode> {
    return Root(node)
  }
}