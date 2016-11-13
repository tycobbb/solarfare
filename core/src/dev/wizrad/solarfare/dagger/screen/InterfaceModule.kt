package dev.wizrad.solarfare.dagger.screen

import dagger.Module
import dagger.Provides
import dev.wizrad.solarfare.game.components.Textures
import dev.wizrad.solarfare.game.components.route.Routes
import dev.wizrad.solarfare.game.ui.MainStage
import dev.wizrad.solarfare.game.ui.minimap.Minimap
import dev.wizrad.solarfare.game.ui.routeline.RouteLine

@Module
class InterfaceModule {
  @Provides
  fun stage(minimap: Minimap, routeLine: RouteLine): MainStage {
    return MainStage(minimap, routeLine)
  }

  @Provides @ScreenScope
  fun minimap(textures: Textures): Minimap {
    return Minimap(textures)
  }

  @Provides
  fun routeLine(routes: Routes): RouteLine {
    return RouteLine(routes)
  }
}
