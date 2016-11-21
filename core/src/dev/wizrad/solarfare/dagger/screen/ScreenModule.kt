package dev.wizrad.solarfare.dagger.screen

import dagger.Module
import dagger.Provides
import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.game.components.Textures
import dev.wizrad.solarfare.game.components.controls.Controls
import dev.wizrad.solarfare.game.components.route.RouteProvider
import dev.wizrad.solarfare.game.components.session.Session

@Module
class ScreenModule {
  @Provides @ScreenScope
  fun session(routes: RouteProvider): Session {
    return Session(routes)
  }

  @Provides @ScreenScope
  fun textures(): Textures {
    return Textures()
  }

  @Provides @ScreenScope
  fun controls(config: Config): Controls {
    return Controls(config)
  }

  @Provides
  fun routes(controls: Controls): RouteProvider {
    return RouteProvider(controls)
  }
}
