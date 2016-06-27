package dev.wizrad.solarfare.game.core

import com.badlogic.gdx.math.Vector2
import dev.wizrad.solarfare.game.renderer.support.set
import dev.wizrad.solarfare.game.world.support.EntitySequence
import dev.wizrad.solarfare.support.geometry.Point

abstract class Entity(
  val parent: Entity?): Updatable {

  // MARK: Properties
  /** A string name for this entity */
  abstract val name:   String
  /** Position in the absolute coordinate space */
  abstract val center: Vector2

  /** Cached list for traversing children in prescribed order */
  private val children: Array<Entity> by lazy { children(EntitySequence()).generate() }

  // MARK: Geometry
  /** Transforms a point from the local -> absolute coordinate space */
  protected fun transform(point: Point): Vector2 {
    return transform(scratch.set(point))
  }

  /** Transforms a vector from the local -> absolute coordinate space */
  protected fun transform(vector: Vector2): Vector2 {
    return vector.add(parent?.center)
  }

  // MARK: Relationships
  open fun children(sequence: EntitySequence): EntitySequence {
    return sequence
  }

  // MARK: Lifecycle
  open fun initialize() {
    for(child in children) {
      child.initialize()
    }
  }

  override fun update(delta: Float) {
    for(child in children) {
      child.update(delta)
    }
  }

  open fun step(delta: Float) {
    for(child in children) {
      child.step(delta)
    }
  }

  open fun afterStep(delta: Float) {
    for(child in children) {
      child.afterStep(delta)
    }
  }

  open fun destroy() {
  }

  companion object {
    val scratch = Vector2(0.0f, 0.0f)
  }
}
