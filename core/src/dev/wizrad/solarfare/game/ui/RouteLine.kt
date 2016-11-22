package dev.wizrad.solarfare.game.ui

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.scenes.scene2d.Group
import dev.wizrad.solarfare.game.components.projection.Projections.Companion.normal
import dev.wizrad.solarfare.game.components.projection.Projections.Companion.stageport
import dev.wizrad.solarfare.game.components.projection.project
import dev.wizrad.solarfare.game.components.route.Route
import dev.wizrad.solarfare.game.components.session.Session
import dev.wizrad.solarfare.support.unwrap

class RouteLine(
  session: Session): Group() {

  private var route: Route? = null
  private var shape = ShapeRenderer()

  init {
    session.currentRoute.subscribe { route = it }
  }

  // MARK: Drawing
  override fun draw(batch: Batch?, parentAlpha: Float) {
    super.draw(batch, parentAlpha)

    buildPath().unwrap {
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

  private fun buildPath(): FloatArray? {
    val route = route?.let { it } ?: return null

    if(route.points.size < 2) {
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
}
