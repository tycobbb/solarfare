package dev.wizrad.solarfare.game.renderer.core

import com.badlogic.gdx.graphics.OrthographicCamera
import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.support.unwrap
import javax.inject.Inject

class Camera @Inject constructor(
  config: Config): OrthographicCamera(), Renderable {

  private val model = config.camera
  private var target: CameraTrackable? = null

  init {
    setToOrtho(true,
      model.width.toFloat(),
      model.height.toFloat()
    )
  }

  // MARK: Tracking
  fun track(target: CameraTrackable) {
    this.target = target
  }

  // MARK: Renderable
  override fun render(delta: Float) {
    target.unwrap {
      position.set(it.point, 0.0f)
      update()
    }
  }
}
