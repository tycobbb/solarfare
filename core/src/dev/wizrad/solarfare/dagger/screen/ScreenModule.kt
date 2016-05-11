package dev.wizrad.solarfare.dagger.screen

import dagger.Module
import dagger.Provides
import dev.wizrad.solarfare.game.shared.Textures

@Module
class ScreenModule {
  @Provides @ScreenScope
  fun textures(): Textures {
    return Textures()
  }
}
