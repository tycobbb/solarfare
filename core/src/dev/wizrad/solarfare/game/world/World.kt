package dev.wizrad.solarfare.game.world

import dev.wizrad.solarfare.game.core.Updatable
import dev.wizrad.solarfare.game.shared.CoordinateSpace
import dev.wizrad.solarfare.game.shared.CoordinateSpace.Kind
import dev.wizrad.solarfare.game.world.core.NodeEntityFactory
import dev.wizrad.solarfare.generation.SpaceNode
import dev.wizrad.solarfare.generation.core.Root
import javax.inject.Inject

class World @Inject constructor(
  root:    Root<SpaceNode>,
  factory: NodeEntityFactory): Updatable {

  // MARK: Children
  val space: Space

  // MARK: Lifecycle
  init {
    space = factory.entity(root.bootstrap())

    // setup the world coordinate space
    CoordinateSpace.registerTransformsFor(Kind.WORLD, byScale = space.size)
  }

  override fun update(delta: Float) {
    space.update(delta)
  }
}
