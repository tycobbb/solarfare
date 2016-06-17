package dev.wizrad.solarfare.game.renderer

import com.badlogic.gdx.graphics.Color
import dev.wizrad.solarfare.game.renderer.core.Renderer
import dev.wizrad.solarfare.game.renderer.support.drawr
import dev.wizrad.solarfare.game.renderer.support.with
import dev.wizrad.solarfare.game.world.Ship

fun Renderer.render(entity: Ship, delta: Float) {
  batch.with(tint = Color.CHARTREUSE) {
    batch.drawr(textures.ship,
      center = entity.center,
      angle  = entity.angle)
  }
}
