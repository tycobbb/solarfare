package dev.wizrad.solarfare.game.renderer.core

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.utils.viewport.Viewport
import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.game.core.Renderable
import dev.wizrad.solarfare.game.core.Targetable
import dev.wizrad.solarfare.support.unwrap
import javax.inject.Inject

class Camera @Inject constructor(
  config: Config): OrthographicCamera(), Renderable {

  // MARK: Properties
  private val viewport: Viewport
  var target: Targetable? = null

  // MARK: Lifecycle
  init {
    viewport = ScreenViewport(this)
    setToOrtho(true)
  }

  override fun update(delta: Float) {
    target.unwrap {
      position.set(it.point, 0.0f)
      update()
    }
  }

  override fun resize(width: Int, height: Int) {
    viewport.update(width, height)
  }
}
