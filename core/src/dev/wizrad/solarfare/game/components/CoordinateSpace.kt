package dev.wizrad.solarfare.game.components

import com.badlogic.gdx.math.Vector2
import dev.wizrad.solarfare.support.extensions.Reflection
import dev.wizrad.solarfare.support.extensions.minv
import dev.wizrad.solarfare.support.extensions.mreflect

data class CoordinateSpace(
  val normalizer:   (Vector2) -> Vector2,
  val denormalizer: (Vector2) -> Vector2) {

  constructor(scale: Vector2, vararg reflecting: Reflection.Axis): this(
    normalizer   = compileNormalizer(scale, reflecting),
    denormalizer = compileDenormalizer(scale, reflecting))

  companion object {
    // MARK: Spaces
    val normal = CoordinateSpace(normalizer = { it }, denormalizer = { it })
    lateinit var touch:     CoordinateSpace
    lateinit var screen:    CoordinateSpace
    lateinit var world:     CoordinateSpace
    lateinit var worldport: CoordinateSpace
    lateinit var stage:     CoordinateSpace
    lateinit var stageport: CoordinateSpace
    lateinit var minimap:   CoordinateSpace

    // MARK: Compilation
    private fun compileNormalizer(scale: Vector2, axes: Array<out Reflection.Axis>): (Vector2) -> Vector2 {
      val inverse    = scale.cpy().minv()
      val reflection = Reflection(axes)

      return { v ->
        v.scl(inverse).mreflect(reflection)
      }
    }

    private fun compileDenormalizer(scale: Vector2, axes: Array<out Reflection.Axis>): (Vector2) -> Vector2 {
      val scale      = scale.cpy()
      val reflection = Reflection(axes)

      return { v ->
        v.mreflect(reflection).scl(scale)
      }
    }

    // MARK: Transforms
    fun transform(position: Vector2, from: CoordinateSpace, to: CoordinateSpace): Vector2 {
      val result = position.cpy()
      mtransform(result, from, to)
      return result
    }

    fun mtransform(position: Vector2, from: CoordinateSpace, to: CoordinateSpace) {
      to.denormalizer(from.normalizer(position))
    }
  }
}
