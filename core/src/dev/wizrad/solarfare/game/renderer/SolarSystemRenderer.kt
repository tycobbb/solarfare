package dev.wizrad.solarfare.game.renderer

import dev.wizrad.solarfare.game.renderer.core.Renderer
import dev.wizrad.solarfare.game.world.SolarSystem

fun Renderer.render(entity: SolarSystem, delta: Float) {
  render(entity.star, delta)
  render(entity.planets, delta)
}

fun Renderer.render(entities: Iterable<SolarSystem>, delta: Float) {
  for(it in entities) render(it, delta)
}
