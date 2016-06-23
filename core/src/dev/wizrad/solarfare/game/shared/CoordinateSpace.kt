package dev.wizrad.solarfare.game.shared

import com.badlogic.gdx.math.Vector2
import dev.wizrad.solarfare.support.extensions.minv
import dev.wizrad.solarfare.support.extensions.mreflect

class CoordinateSpace {
  // MARK: Support Types
  enum class Kind { World, Minimap }

  data class Transforms(
    val normalizer:   (Vector2) -> Vector2,
    val denormalizer: (Vector2) -> Vector2) {
  }

  // MARK: Static Storage
  companion object {
    val scratch = Vector2(0.0f, 0.0f)

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
        Kind.World   -> world!!
        Kind.Minimap -> minimap!!
      }
    }

    // MARK: Registration
    fun registerTransformsFor(kind: Kind, transforms: Transforms) {
      when (kind) {
        Kind.World   -> world   = transforms
        Kind.Minimap -> minimap = transforms
      }
    }

    fun registerTransformsFor(kind: CoordinateSpace.Kind, byScale: Vector2, reflected: Boolean) {
      val scale      = byScale.cpy()
      val inverse    = scale.cpy().minv()
      val reflection = if(reflected) 1.0f else null

      registerTransformsFor(kind, CoordinateSpace.Transforms(
        normalizer   = { position -> position.scl(inverse).mreflect(y = reflection) },
        denormalizer = { position -> position.mreflect(y = reflection).scl(scale) }
      ))
    }
  }
}

// MARK: Exports
fun coordinateSpace(kind: CoordinateSpace.Kind, byScale: Vector2, reflected: Boolean = false) {
  CoordinateSpace.registerTransformsFor(kind, byScale, reflected)
}

fun transform(position: Vector2, from: CoordinateSpace.Kind, to: CoordinateSpace.Kind): Vector2 {
  val scratch = CoordinateSpace.scratch.set(position)
  mtransform(scratch, from, to)
  return scratch
}

fun mtransform(position: Vector2, from: CoordinateSpace.Kind, to: CoordinateSpace.Kind) {
  CoordinateSpace.mtransform(position, from, to)
}
