package dev.wizrad.solarfare.game.renderer

import com.badlogic.gdx.graphics.g2d.TextureRegion
import dev.wizrad.solarfare.game.renderer.core.Renderer
import dev.wizrad.solarfare.game.world.Spheroid
import dev.wizrad.solarfare.generation.SpheroidNode

fun <N: SpheroidNode> Renderer.render(entity: Spheroid<N>, delta: Float, texture: TextureRegion) {
  batch.draw(texture,
    entity.center.x - entity.radius,
    entity.center.y - entity.radius,
    entity.radius,
    entity.radius
  )
}
