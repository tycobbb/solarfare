package dev.wizrad.solarfare.dagger.screen

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import dagger.Module
import dagger.Provides
import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.game.renderer.core.Camera
import dev.wizrad.solarfare.game.renderer.core.Renderer
import dev.wizrad.solarfare.game.shared.Controls
import dev.wizrad.solarfare.game.shared.Textures
import dev.wizrad.solarfare.game.ui.Minimap
import dev.wizrad.solarfare.game.world.Entities
import dev.wizrad.solarfare.game.world.core.NodeEntityFactory
import dev.wizrad.solarfare.generation.SpaceNode
import dev.wizrad.solarfare.generation.core.Root

@Module
class EntityModule {
  // MARK: Model / Physics
  @Provides @ScreenScope
  fun world(): World {
    return World(Vector2(0.0f, 0.0f), true)
  }

  @Provides @ScreenScope
  fun entities(world: World, root: Root<SpaceNode>, factory: NodeEntityFactory): Entities {
    return Entities(world, root, factory)
  }

  @Provides
  fun factory(world: World, controls: Controls, minimap: Minimap): NodeEntityFactory {
    return NodeEntityFactory(world, controls, minimap)
  }

  // MARK: View
  @Provides
  fun renderer(entities: Entities, camera: Camera, textures: Textures): Renderer {
    return Renderer(entities, camera, textures)
  }

  @Provides
  fun camera(config: Config): Camera {
    return Camera(config)
  }
}
