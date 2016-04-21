package dev.wizrad.solarfare.game.world

import dev.wizrad.solarfare.generation.SpaceNode
import dev.wizrad.solarfare.generation.core.Root
import javax.inject.Inject

class World @Inject constructor(
  root: Root<SpaceNode>) {

  private val space: Space

  init {
    space = Space(root.bootstrap())
  }

  fun update(delta: Float) {

  }
}
