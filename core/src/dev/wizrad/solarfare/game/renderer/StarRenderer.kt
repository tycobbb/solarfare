package dev.wizrad.solarfare.game.renderer

import dev.wizrad.solarfare.game.renderer.core.Renderer
import dev.wizrad.solarfare.game.world.Star

fun Renderer.render(entity: Star, delta: Float) {

}

fun Renderer.render(entities: Iterable<Star>, delta: Float) {
  for(it in entities) render(it, delta)
}
