package dev.wizrad.solarfare.game.renderer.functions

import dev.wizrad.solarfare.game.renderer.Renderer
import dev.wizrad.solarfare.game.world.SolarSystem

fun Renderer.render(entity: SolarSystem) {
  render(entity.star)
  render(entity.planets)
}

fun Renderer.render(entities: Iterable<SolarSystem>) {
  for(it in entities) render(it)
}
