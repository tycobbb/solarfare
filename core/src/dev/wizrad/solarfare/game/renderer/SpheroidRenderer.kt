package dev.wizrad.solarfare.game.renderer

import com.badlogic.gdx.graphics.g2d.TextureRegion
import dev.wizrad.solarfare.game.renderer.core.Renderer
import dev.wizrad.solarfare.game.renderer.support.drawc
import dev.wizrad.solarfare.game.world.Spheroid
import dev.wizrad.solarfare.generation.SpheroidNode

fun <N: SpheroidNode> Renderer.render(entity: Spheroid<N>, delta: Float, texture: TextureRegion) {
  batch.drawc(texture,
    center = entity.center,
    radius = entity.radius)
}
