package dev.wizrad.solarfare.game.renderer.core

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.utils.viewport.Viewport
import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.game.components.CoordinateSpace
import dev.wizrad.solarfare.game.components.CoordinateSpace.Companion.world
import dev.wizrad.solarfare.game.components.CoordinateSpace.Companion.worldport
import dev.wizrad.solarfare.game.core.Renderable
import dev.wizrad.solarfare.game.core.Targetable
import dev.wizrad.solarfare.support.extensions.update
import dev.wizrad.solarfare.support.then
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

    val normalizer   = { v: Vector2 -> v.add(p.x - o.x, p.y - o.y) }
    val denormalizer = { v: Vector2 -> v.sub(p.x - o.x, p.y - o.y) }

    CoordinateSpace.worldport = CoordinateSpace(
      normalizer   = normalizer then world.normalizer,
      denormalizer = world.denormalizer then denormalizer
    )

    val reflect = { v: Vector2 -> v.update(y = height - v.y) }
    CoordinateSpace.stageport = CoordinateSpace(
      normalizer   = reflect then worldport.normalizer,
      denormalizer = worldport.denormalizer then reflect
    )
  }
}
