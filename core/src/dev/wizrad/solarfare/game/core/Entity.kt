package dev.wizrad.solarfare.game.core

import com.badlogic.gdx.math.Vector2
import dev.wizrad.solarfare.game.renderer.support.set
import dev.wizrad.solarfare.support.geometry.Point

abstract class Entity(
  val parent: Entity?): Updatable {

  // MARK: Geometry
  /** Position in the absolute coordinate space */
  val center = Vector2.Zero.cpy()

  /** Transforms a point from the local -> absolute coordinate space */
  protected fun transform(point: Point): Vector2 {
    return transform(scratch.set(point))
  }

  /** Transforms a vector from the local -> absolute coordinate space */
  protected fun transform(vector: Vector2): Vector2 {
    return vector.add(parent?.center)
  }

  // MARK: Lifecycle
  override fun update(delta: Float) {
  }

  open fun destroy() {
  }

  companion object {
    val scratch = Vector2.Zero.cpy()
  }
}
