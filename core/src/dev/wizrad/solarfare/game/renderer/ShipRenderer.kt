package dev.wizrad.solarfare.game.renderer

import com.badlogic.gdx.graphics.Color
import dev.wizrad.solarfare.game.renderer.core.Renderer
import dev.wizrad.solarfare.game.renderer.support.circle
import dev.wizrad.solarfare.game.renderer.support.draw
import dev.wizrad.solarfare.game.world.Ship

fun Renderer.render(entity: Ship, delta: Float) {
  shapeRenderer.draw {
    it.color = Color.CHARTREUSE
    it.circle(entity.center, 4.0f)
  }
}
