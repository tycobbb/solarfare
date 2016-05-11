package dev.wizrad.solarfare.game.renderer

import dev.wizrad.solarfare.game.renderer.core.Renderer
import dev.wizrad.solarfare.game.world.Ship

fun Renderer.render(entity: Ship, delta: Float) {
  batch.draw(textures.ship,
    entity.point.x - textures.ship.regionWidth,
    entity.point.y - textures.ship.regionHeight
  )
}
