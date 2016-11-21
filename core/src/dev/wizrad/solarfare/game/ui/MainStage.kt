package dev.wizrad.solarfare.game.ui

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import dev.wizrad.solarfare.game.components.projection.Projection
import dev.wizrad.solarfare.game.components.projection.Projections
import dev.wizrad.solarfare.game.components.projection.then
import dev.wizrad.solarfare.game.core.Renderable
import dev.wizrad.solarfare.game.ui.minimap.Minimap
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
    Projections.stage = Projection.reflecting(y = height) then Projection.scaling(width, height)
  }
}
