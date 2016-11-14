package dev.wizrad.solarfare.game.renderer.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import dev.wizrad.solarfare.game.components.Textures
import dev.wizrad.solarfare.game.core.Renderable
import dev.wizrad.solarfare.game.renderer.render
import dev.wizrad.solarfare.game.world.EntityWorld
import javax.inject.Inject

class Renderer @Inject constructor(
  val world:    EntityWorld,
  val camera:   Camera,
  val textures: Textures): Renderable {

  // MARK: Renderers
  val batch = SpriteBatch()
  val shapeRenderer = ShapeRenderer()

  // MARK: Lifecycle
  init {
    camera.target = world.space.ship
  }

  override fun update(delta: Float) {
    // render background color to prevent flickering
    Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // reposition the camera
    camera.update(delta)
    batch.projectionMatrix = camera.combined
    shapeRenderer.projectionMatrix = camera.combined

    // render the world / map
    batch.begin()
    render(world.space, delta)
    batch.end()
  }

  override fun resize(width: Int, height: Int) {
    camera.resize(width, height)
  }
}
