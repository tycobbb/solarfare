package dev.wizrad.solarfare.game.renderer

import com.badlogic.gdx.graphics.Color
import dev.wizrad.solarfare.game.renderer.core.Renderer
import dev.wizrad.solarfare.game.renderer.support.draw
import dev.wizrad.solarfare.game.renderer.support.rect
import dev.wizrad.solarfare.game.world.Space

fun Renderer.render(entity: Space, delta: Float) {
  batch.end()
  shapeRenderer.draw {
    it.color = Color(0x131313FF)
    it.rect(entity.size)
  }
  batch.begin()

  render(entity.systems, delta)
  render(entity.ship,    delta)
}
