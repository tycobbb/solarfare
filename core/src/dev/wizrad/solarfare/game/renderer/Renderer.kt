package dev.wizrad.solarfare.game.renderer

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import dev.wizrad.solarfare.game.renderer.functions.render
import dev.wizrad.solarfare.game.world.World
import javax.inject.Inject

class Renderer @Inject constructor(
  private val world:  World,
  private val camera: Camera) {

  fun render() {
    // render background color to prevent flickering
    Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    render(world.space)
  }
}
