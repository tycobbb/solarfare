package dev.wizrad.solarfare.game.renderer.functions

import dev.wizrad.solarfare.game.renderer.Renderer
import dev.wizrad.solarfare.game.world.Planet

fun Renderer.render(entity: Planet) {

}

fun Renderer.render(entities: Iterable<Planet>) {
  for(it in entities) render(it)
}
