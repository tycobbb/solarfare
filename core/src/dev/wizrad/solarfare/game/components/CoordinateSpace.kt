package dev.wizrad.solarfare.game.components

import com.badlogic.gdx.math.Vector2
import dev.wizrad.solarfare.support.extensions.minv
import dev.wizrad.solarfare.support.extensions.mreflect

data class CoordinateSpace(
  val normalizer:   (Vector2) -> Vector2,
  val denormalizer: (Vector2) -> Vector2) {

  companion object {
    // MARK: Storage
    val scratch = Vector2(0.0f, 0.0f)

    // MARK: Spaces
    lateinit var screen:  CoordinateSpace
    lateinit var world:   CoordinateSpace
    lateinit var stage:   CoordinateSpace
    lateinit var minimap: CoordinateSpace

    // MARK: Factories
    fun create(scale: Vector2, isReflected: Boolean = false): CoordinateSpace {
      val scale = scale.cpy()
      val inverse = scale.cpy().minv()
      val reflection = if (isReflected) 1.0f else null

      return CoordinateSpace(
        normalizer   = { v -> v.scl(inverse).mreflect(y = reflection) },
        denormalizer = { v -> v.mreflect(y = reflection).scl(scale) }
      )
    }

    // MARK: Transforms
    fun transform(position: Vector2, from: CoordinateSpace, to: CoordinateSpace): Vector2 {
      val scratch = scratch.set(position)
      mtransform(scratch, from, to)
      return scratch
    }

    fun mtransform(position: Vector2, from: CoordinateSpace, to: CoordinateSpace) {
      to.denormalizer(from.normalizer(position))
    }
  }
}
