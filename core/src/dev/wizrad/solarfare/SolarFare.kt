package dev.wizrad.solarfare

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import dev.wizrad.solarfare.dagger.DaggerGameComponent
import dev.wizrad.solarfare.dagger.GameComponent
import dev.wizrad.solarfare.dagger.GameModule
import dev.wizrad.solarfare.dagger.GenerationModule
import dev.wizrad.solarfare.generation.SpaceNode
import dev.wizrad.solarfare.generation.core.Root
import javax.inject.Inject

class SolarFare : ApplicationAdapter() {
  lateinit protected var component: GameComponent private set
  lateinit protected var root: Root<SpaceNode> @Inject set

  override fun create() {
    component = buildComponent()
    component.inject(this)

    // bootstrap world generation
    root.bootstrap()
  }

  override fun render() {
    Gdx.gl.glClearColor(0.3f, 0.4f, 0.5f, 1f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
  }

  //
  // Injection
  private fun buildComponent(): GameComponent {
    return DaggerGameComponent.builder()
      .gameModule(GameModule())
      .generationModule(GenerationModule())
      .build()
  }
}