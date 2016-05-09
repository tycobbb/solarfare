package dev.wizrad.solarfare.game.renderer.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import dev.wizrad.solarfare.game.core.Renderable
import dev.wizrad.solarfare.game.renderer.render
import dev.wizrad.solarfare.game.world.World
import javax.inject.Inject

class Renderer @Inject constructor(
  val world:  World,
  val camera: Camera): Renderable {

  // MARK: Renderers
  val shapeRenderer: ShapeRenderer = ShapeRenderer()

  // MARK: Lifecycle
  init {
    camera.track(world.space.trackable)
  }

  override fun update(delta: Float) {
    // render background color to prevent flickering
    Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // reposition the camera
    camera.update(delta)
    shapeRenderer.projectionMatrix = camera.combined

    // render the world / map
    render(world.space, delta)
  }

  override fun resize(width: Int, height: Int) {
    camera.resize(width, height)
  }
}
