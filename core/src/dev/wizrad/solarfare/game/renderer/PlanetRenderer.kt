package dev.wizrad.solarfare.game.renderer

import dev.wizrad.solarfare.game.renderer.core.Renderer
import dev.wizrad.solarfare.game.world.Planet

fun Renderer.render(entity: Planet, delta: Float) {

}

fun Renderer.render(entities: Iterable<Planet>, delta: Float) {
  for(it in entities) render(it, delta)
}
