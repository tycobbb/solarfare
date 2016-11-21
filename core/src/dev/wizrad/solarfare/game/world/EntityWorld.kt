package dev.wizrad.solarfare.game.world

import com.badlogic.gdx.math.Vector2
import dev.wizrad.solarfare.game.components.controls.Controls
import dev.wizrad.solarfare.game.components.projection.Projection
import dev.wizrad.solarfare.game.components.projection.Projections
import dev.wizrad.solarfare.game.components.route.RouteProvider
import dev.wizrad.solarfare.game.core.Updatable
import dev.wizrad.solarfare.game.ui.minimap.Minimap
import dev.wizrad.solarfare.game.world.core.NodeEntityFactory
import dev.wizrad.solarfare.generation.SpaceNode
import dev.wizrad.solarfare.generation.core.Root
import dev.wizrad.solarfare.support.extensions.min
import javax.inject.Inject
import com.badlogic.gdx.physics.box2d.World as PhysicsWorld

class EntityWorld @Inject constructor(
  root: Root<SpaceNode>,
  override val controls: Controls,
  override val minimap:  Minimap,
  override val routes: RouteProvider): Updatable, World {

  // MARK: World
  override val physics = PhysicsWorld(Vector2(0.0f, 0.0f), true)

  // MARK: Children
  val space: Space

  // MARK: Properties
  /** accumulates frame time to determine when to run fixed-step physics simulation */
  private var accumulator: Float = 0.0f

  // MARK: Lifecycle
  init {
    // setup initial conditions
    val factory = NodeEntityFactory(world = this)
    space = factory.entity(node = root.bootstrap())
    space.initialize()

    // routing should target the user's ship
    routes.target = space.ship

    // setup the world coordinate space
    Projections.world = Projection.scaling(space.size)
  }

  override fun update(delta: Float) {
    // update model layer each render frame
    space.update(delta)

    // update physics according to fixed time step
    // See: http://gafferongames.com/game-physics/fix-your-timestep/
    val frame = min(delta, 0.25f)
    accumulator += frame

    while(accumulator >= timestep) {
      space.step(timestep)
      physics.step(timestep, 6, 2)
      space.afterStep(timestep)

      accumulator -= timestep
    }
  }

  companion object {
    // MARK: Constants
    private val timestep = 1.0f / 60.0f
  }
}
