package dev.wizrad.solarfare.dagger.screen

import dagger.Module
import dagger.Provides
import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.game.renderer.Camera
import dev.wizrad.solarfare.game.renderer.Renderer
import dev.wizrad.solarfare.game.world.World
import dev.wizrad.solarfare.generation.SpaceNode
import dev.wizrad.solarfare.generation.core.Root

@Module
class ScreenModule {
  @Provides @ScreenScope
  fun world(root: Root<SpaceNode>): World {
    return World(root)
  }

  @Provides
  fun renderer(world: World, camera: Camera): Renderer {
    return Renderer(world, camera)
  }

  @Provides
  fun camera(config: Config): Camera {
    return Camera(config)
  }
}
