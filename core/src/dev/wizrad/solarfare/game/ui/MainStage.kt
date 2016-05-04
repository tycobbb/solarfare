package dev.wizrad.solarfare.game.ui

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import dev.wizrad.solarfare.game.core.Updatable
import javax.inject.Inject

class MainStage @Inject constructor(
  minimap: Minimap): Stage(ScreenViewport()), Updatable {

  init {
    addActor(minimap)
  }

  override fun update(delta: Float) {
    act(delta)
    draw()
  }
}
