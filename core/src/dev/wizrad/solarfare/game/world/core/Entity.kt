package dev.wizrad.solarfare.game.world.core

import dev.wizrad.solarfare.generation.core.Node

abstract class Entity<N: Node>(
  node: N): Updatable {

  override fun update(delta: Float) {

  }
}
