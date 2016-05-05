package dev.wizrad.solarfare.game.shared

import com.badlogic.gdx.math.Vector2

class CoordinateSpace {
  // MARK: Support Types
  enum class Kind { WORLD, MINIMAP }

  data class Transforms(
    val normalizer:   (Vector2) -> Vector2,
    val denormalizer: (Vector2) -> Vector2) {
  }

  // MARK: Static Storage
  companion object {
    val scratch = Vector2.Zero.cpy()

    // MARK: Transforms
    private var world:   Transforms? = null
    private var minimap: Transforms? = null

    // MARK: Transformation
    fun mtransform(position: Vector2, fromKind: Kind, toKind: Kind): Vector2 {
      val source      = transformsFor(fromKind).normalizer
      val destination = transformsFor(toKind).denormalizer

      return destination(source(position))
    }

    private fun transformsFor(kind: Kind): Transforms {
      return when (kind) {
        Kind.WORLD   -> world!!
        Kind.MINIMAP -> minimap!!
      }
    }

    // MARK: Registration
    fun registerTransformsFor(kind: Kind, transforms: Transforms) {
      when (kind) {
        Kind.WORLD   -> world   = transforms
        Kind.MINIMAP -> minimap = transforms
      }
    }

    fun registerTransformsFor(kind: CoordinateSpace.Kind, byScale: Vector2) {
      val scale   = byScale.cpy()
      val inverse = Vector2(
        1.0f / scale.x,
        1.0f / scale.y
      )

      registerTransformsFor(kind, CoordinateSpace.Transforms(
        normalizer   = { position -> position.scl(inverse) },
        denormalizer = { position -> position.scl(scale) }
      ))
    }
  }
}

// MARK: Exports
fun transform(position: Vector2, from: CoordinateSpace.Kind, to: CoordinateSpace.Kind): Vector2 {
  val scratch = CoordinateSpace.scratch.set(position)
  mtransform(scratch, from, to)
  return scratch
}

fun mtransform(position: Vector2, from: CoordinateSpace.Kind, to: CoordinateSpace.Kind) {
  CoordinateSpace.mtransform(position, from, to)
}
