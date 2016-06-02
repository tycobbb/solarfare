package dev.wizrad.solarfare.dagger.screen

import dagger.Module
import dagger.Provides
import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.game.shared.Controls
import dev.wizrad.solarfare.game.shared.Textures

@Module
class ScreenModule {
  @Provides @ScreenScope
  fun textures(): Textures {
    return Textures()
  }

  @Provides
  fun controls(config: Config): Controls {
    return Controls(config)
  }
}
