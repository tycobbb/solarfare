package dev.wizrad.solarfare.game.renderer

import com.badlogic.gdx.graphics.OrthographicCamera
import dev.wizrad.solarfare.config.Config
import javax.inject.Inject

class Camera @Inject constructor(
  config: Config): OrthographicCamera() {

  private val model = config.camera

  init {
    setToOrtho(true,
      model.width.toFloat(),
      model.height.toFloat()
    )
  }
}
