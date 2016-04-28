package dev.wizrad.solarfare.game.renderer

import com.badlogic.gdx.graphics.Color
import dev.wizrad.solarfare.game.renderer.core.Renderer
import dev.wizrad.solarfare.game.renderer.support.draw
import dev.wizrad.solarfare.game.renderer.support.rect
import dev.wizrad.solarfare.game.world.Space

fun Renderer.render(entity: Space, delta: Float) {
  shapeRenderer.draw {
    it.color = Color.PURPLE
    it.rect(entity.size)
  }

  render(entity.ship, delta)
  render(entity.solarSystems, delta)
}
