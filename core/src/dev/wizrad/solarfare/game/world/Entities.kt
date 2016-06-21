package dev.wizrad.solarfare.game.world

import com.badlogic.gdx.physics.box2d.World
import dev.wizrad.solarfare.game.core.Updatable
import dev.wizrad.solarfare.game.shared.CoordinateSpace.Kind
import dev.wizrad.solarfare.game.shared.coordinateSpace
import dev.wizrad.solarfare.game.world.core.NodeEntityFactory
import dev.wizrad.solarfare.generation.SpaceNode
import dev.wizrad.solarfare.generation.core.Root
import dev.wizrad.solarfare.support.extensions.max
import javax.inject.Inject

class Entities @Inject constructor(
  world:   World,
  root:    Root<SpaceNode>,
  factory: NodeEntityFactory): Updatable {

  // MARK: Children
  val space: Space = factory.entity(root.bootstrap())
  val world: World = world

  // MARK: Properties
  /** accumulates frame time to determine when to run fixed-step physics simulation */
  private var accumulator: Float = 0.0f

  // MARK: Lifecycle
  init {
    // setup the world coordinate space
    coordinateSpace(Kind.World, byScale = space.size)
  }

  override fun update(delta: Float) {
    // update model layer each render frame
    space.update(delta)

    // update physics according to fixed time step
    // See: http://gafferongames.com/game-physics/fix-your-timestep/
    val frame = max(delta, 0.25f)
    accumulator += frame
    while(accumulator >= timestep) {
      world.step(timestep, 6, 2)
      space.afterStep(timestep)

      accumulator -= timestep
    }
  }

  companion object {
    // MARK: Constants
    private val timestep = 1.0f / 60.0f
  }
}
