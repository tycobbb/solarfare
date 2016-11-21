package dev.wizrad.solarfare.game.renderer.core

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.utils.viewport.Viewport
import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.game.components.projection.Projection
import dev.wizrad.solarfare.game.components.projection.Projections
import dev.wizrad.solarfare.game.components.projection.then
import dev.wizrad.solarfare.game.core.Renderable
import dev.wizrad.solarfare.game.core.Targetable
import dev.wizrad.solarfare.support.unwrap
import javax.inject.Inject

class Camera @Inject constructor(
  config: Config): OrthographicCamera(), Renderable {

  // MARK: Properties
  private val viewport: Viewport = ScreenViewport(this)
  var target: Targetable? = null

  // MARK: Lifecycle
  init {
    setToOrtho(true)
  }

  override fun update(delta: Float) {
    target.unwrap {
      position.set(it.position, 0.0f)
      update()
    }
  }

  override fun resize(width: Int, height: Int) {
    viewport.update(width, height)
    resizeProjections(width, height)
  }

  private fun resizeProjections(width: Int, height: Int) {
    val p = position
    val o = Vector2(width / 2.0f, height/ 2.0f)

    val reflect = Projection.reflecting(y = height)
    val offset  = Projection(
      normalizer   = { it.add(p.x - o.x, p.y - o.y) },
      denormalizer = { it.sub(p.x - o.x, p.y - o.y) }
    )

    Projections.viewport  = offset  then Projections.world
    Projections.stageport = reflect then offset then Projections.world
  }
}
