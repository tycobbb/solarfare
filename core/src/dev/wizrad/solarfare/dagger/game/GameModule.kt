package dev.wizrad.solarfare.dagger.game

import dagger.Module
import dagger.Provides
import dev.wizrad.solarfare.config.Config
import javax.inject.Singleton

@Module
class GameModule: GameProvider {
  @Provides @Singleton
  override fun config(): Config {
    return Config.load()
  }
}
