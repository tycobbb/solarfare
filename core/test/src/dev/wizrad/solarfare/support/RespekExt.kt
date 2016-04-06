package dev.wizrad.solarfare.support

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.headless.HeadlessApplication
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration
import com.badlogic.gdx.graphics.GL20
import dev.wizrad.respek.dsl.Context
import dev.wizrad.solarfare.SolarFare
import org.mockito.Mockito.*;


fun Context.loadGdx() {
  before {
    // bootstrap headless app
    val config = HeadlessApplicationConfiguration()
    config.renderInterval = 1.0f / 60.0f
    HeadlessApplication(SolarFare(), config)

    // mock out any necessary static objects
    Gdx.gl = mock(GL20::class.java)
  }
}
