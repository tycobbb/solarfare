package dev.wizrad.solarfare.game.renderer

import com.badlogic.gdx.graphics.Color
import dev.wizrad.solarfare.game.renderer.core.Renderer
import dev.wizrad.solarfare.game.renderer.support.circle
import dev.wizrad.solarfare.game.renderer.support.draw
import dev.wizrad.solarfare.game.world.Star

fun Renderer.render(entity: Star, delta: Float) {
  batch.end()
  shapeRenderer.draw {
    it.color = Color.GOLDENROD
    it.circle(entity.center, entity.radius * 3) // TODO: remove arbitrary scale
  }
  batch.begin()
}

fun Renderer.render(entities: Iterable<Star>, delta: Float) {
  for(it in entities) render(it, delta)
}
