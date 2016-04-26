package dev.wizrad.solarfare.game.renderer.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import dev.wizrad.solarfare.game.renderer.render
import dev.wizrad.solarfare.game.world.World
import javax.inject.Inject

class Renderer @Inject constructor(
  val world: World,
  val camera: Camera): Renderable {

  // MARK: Renderers
  val shapeRenderer: ShapeRenderer

  // MARK: Lifecycle
  init {
    camera.track(world.space.trackable)

    shapeRenderer = ShapeRenderer()
    shapeRenderer.projectionMatrix = camera.combined
  }

  // MARK: Renderable
  override fun render(delta: Float) {
    // render background color to prevent flickering
    Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // render the world
    render(world.space, delta)

    // reposition the camera
    camera.render(delta)
  }
}
