package dev.wizrad.solarfare.game

import com.badlogic.gdx.Screen
import dev.wizrad.solarfare.dagger.game.GameComponent
import dev.wizrad.solarfare.dagger.screen.*
import dev.wizrad.solarfare.game.renderer.core.Renderer
import dev.wizrad.solarfare.game.ui.MainStage
import dev.wizrad.solarfare.game.world.Entities
import javax.inject.Inject

class MainScreen(
  gameComponent: GameComponent): Screen {

  private lateinit var component: ScreenComponent

  // MARK: Dependencies
  private lateinit var entities: Entities
  private lateinit var renderer: Renderer
  private lateinit var stage:    MainStage

  // MARK: Initialization
  init {
    component = componentFrom(gameComponent)
    component.inject(this)
  }

  @Inject
  fun inject(entities: Entities, renderer: Renderer, stage: MainStage) {
    this.entities = entities
    this.renderer = renderer
    this.stage    = stage
  }

  // MARK: Screen
  override fun render(delta: Float) {
    entities.update(delta)
    renderer.update(delta)
    stage.update(delta)
  }

  override fun resize(width: Int, height: Int) {
    renderer.resize(width, height)
    stage.resize(width, height)
  }

  override fun show() {
  }

  override fun hide() {
  }

  override fun pause() {
  }

  override fun resume() {
  }

  override fun dispose() {
  }

  // MARK: Component
  private fun componentFrom(gameComponent: GameComponent): ScreenComponent {
    return DaggerScreenComponent.builder()
      .gameComponent(gameComponent)
      .screenModule(ScreenModule())
      .entityModule(EntityModule())
      .interfaceModule(InterfaceModule())
      .generationModule(GenerationModule())
      .build()
  }
}
