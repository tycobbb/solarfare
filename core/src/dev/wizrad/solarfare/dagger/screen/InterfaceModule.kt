package dev.wizrad.solarfare.dagger.screen

import dagger.Module
import dagger.Provides
import dev.wizrad.solarfare.game.shared.Textures
import dev.wizrad.solarfare.game.ui.MainStage
import dev.wizrad.solarfare.game.ui.minimap.Minimap

@Module
class InterfaceModule {
  @Provides
  fun stage(minimap: Minimap): MainStage {
    return MainStage(minimap)
  }

  @Provides @ScreenScope
  fun minimap(textures: Textures): Minimap {
    return Minimap(textures)
  }
}
