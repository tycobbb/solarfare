package dev.wizrad.solarfare.game.ui

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.scenes.scene2d.Group
import dev.wizrad.solarfare.game.components.controls.Touch
import dev.wizrad.solarfare.game.components.projection.Projections.Companion.normal
import dev.wizrad.solarfare.game.components.projection.Projections.Companion.stageport
import dev.wizrad.solarfare.game.components.projection.project
import dev.wizrad.solarfare.game.components.route.Route
import dev.wizrad.solarfare.game.components.route.Routes
import dev.wizrad.solarfare.support.unwrap

class RouteLine(
  routes: Routes): Group() {

  private var path: FloatArray? = null
  private var shape = ShapeRenderer()

  init {
    routes.stream
      .subscribe { path = buildPath(route = it) }
  }

  private fun buildPath(route: Route): FloatArray? {
    if(route.event == Touch.Event.Ended || route.points.size < 2) {
      return null
    }

    var i = 0
    val result = FloatArray(route.points.size * 2)

    for(point in route.points) {
      val local = project(point, from = normal, to = stageport)
      result[i++] = local.x
      result[i++] = local.y
    }

    return result
  }

  // MARK: Drawing
  override fun draw(batch: Batch?, parentAlpha: Float) {
    super.draw(batch, parentAlpha)

    path.unwrap {
      batch?.end()
      drawPath(it)
      batch?.begin()
    }
  }

  private fun drawPath(path: FloatArray) {
    shape.begin(ShapeType.Line)
    shape.polyline(path)
    shape.end()
  }
}
