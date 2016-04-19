package dev.wizrad.solarfare

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import dev.wizrad.solarfare.dagger.game.DaggerGameComponent
import dev.wizrad.solarfare.dagger.game.GameComponent
import dev.wizrad.solarfare.dagger.game.GameModule
import dev.wizrad.solarfare.game.MainScreen

class SolarFare: Game() {
  private lateinit var component: GameComponent

  override fun create() {
    Gdx.app.logLevel = Application.LOG_DEBUG

    component = buildComponent()
    component.inject(this)

    setScreen(MainScreen(component))
  }

  //
  // Injection
  private fun buildComponent(): GameComponent {
    return DaggerGameComponent.builder()
      .gameModule(GameModule())
      .build()
  }
}