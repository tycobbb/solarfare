package dev.wizrad.solarfare.game.renderer.functions

import dev.wizrad.solarfare.game.renderer.Renderer
import dev.wizrad.solarfare.game.world.Space

fun Renderer.render(entity: Space) {
  render(entity.ship)
  render(entity.solarSystems)
}
