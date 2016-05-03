package dev.wizrad.solarfare.game.renderer.core

import com.badlogic.gdx.graphics.OrthographicCamera
import dev.wizrad.solarfare.config.Config
import dev.wizrad.solarfare.game.core.Updatable
import dev.wizrad.solarfare.support.unwrap
import javax.inject.Inject

class Camera @Inject constructor(
  config: Config): OrthographicCamera(), Updatable {

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

  // MARK: Updatable
  override fun update(delta: Float) {
    target.unwrap {
      position.set(it.point, 0.0f)
      update()
    }
  }
}
