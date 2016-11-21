package dev.wizrad.solarfare.game.components.projection

import com.badlogic.gdx.math.Vector2
import dev.wizrad.solarfare.support.then

fun project(point: Vector2, from: Projection, to: Projection): Vector2 {
  val result = point.cpy()
  to.denormalizer(from.normalizer(result))
  return result
}

infix fun Projection.then(other: Projection): Projection {
  return Projection(
    normalizer   = normalizer then other.normalizer,
    denormalizer = other.denormalizer then denormalizer
  )
}
