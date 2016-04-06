package dev.wizrad.solarfare

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dev.wizrad.solarfare.config.Config

class SolarFare : ApplicationAdapter() {
  lateinit var config: Config

  override fun create() {

  }

  override fun render() {
    Gdx.gl.glClearColor(0.3f, 0.4f, 0.5f, 1f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
  }
}