package dev.wizrad.solarfare

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import dev.wizrad.solarfare.dagger.DaggerGameComponent
import dev.wizrad.solarfare.dagger.GameComponent
import dev.wizrad.solarfare.dagger.GameModule
import dev.wizrad.solarfare.dagger.GenerationModule
import dev.wizrad.solarfare.game.MainScreen
import javax.inject.Inject

class SolarFare: Game() {
  lateinit protected var component: GameComponent private set

  override fun create() {
    Gdx.app.logLevel = Application.LOG_DEBUG

    component = buildComponent()
    component.inject(this)
  }

  @Inject fun start(startScreen: MainScreen) {
    setScreen(startScreen)
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