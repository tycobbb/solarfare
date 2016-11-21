package dev.wizrad.solarfare.game.ui

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import dev.wizrad.solarfare.game.components.CoordinateSpace
import dev.wizrad.solarfare.game.core.Renderable
import dev.wizrad.solarfare.game.ui.minimap.Minimap
import dev.wizrad.solarfare.support.extensions.Reflection.Axis
import dev.wizrad.solarfare.support.extensions.Vector2
import javax.inject.Inject

class MainStage @Inject constructor(
  minimap:   Minimap,
  routeLine: RouteLine): Stage(ScreenViewport()), Renderable {

  // MARK: Lifecycle
  init {
    addActor(minimap)
    addActor(routeLine)
  }

  override fun update(delta: Float) {
    act(delta)
    draw()
  }

  override fun resize(width: Int, height: Int) {
    viewport.update(width, height, true)
    CoordinateSpace.stage = CoordinateSpace(scale = Vector2(width, height), reflecting = Axis.Y)
  }
}
