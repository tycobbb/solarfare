package dev.wizrad.solarfare.game.world.core

import com.badlogic.gdx.math.Vector2
import dev.wizrad.solarfare.generation.core.Node

abstract class Entity<N: Node>(
  node: N): Updatable {

  // MARK: Geometry
  val center = Vector2.Zero

  // MARK: Updatable
  override fun update(delta: Float) {

  }
}
