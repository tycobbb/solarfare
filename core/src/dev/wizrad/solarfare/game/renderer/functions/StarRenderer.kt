package dev.wizrad.solarfare.game.renderer.functions

import dev.wizrad.solarfare.game.renderer.Renderer
import dev.wizrad.solarfare.game.world.Star

fun Renderer.render(entity: Star) {

}

fun Renderer.render(entities: Iterable<Star>) {
  for(it in entities) render(it)
}
