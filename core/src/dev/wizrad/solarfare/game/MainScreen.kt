package dev.wizrad.solarfare.game

import com.badlogic.gdx.Screen
import dev.wizrad.solarfare.dagger.game.GameComponent
import dev.wizrad.solarfare.dagger.screen.*
import dev.wizrad.solarfare.game.renderer.core.Renderer
import dev.wizrad.solarfare.game.ui.MainStage
import dev.wizrad.solarfare.game.world.World
import javax.inject.Inject

class MainScreen(
  gameComponent: GameComponent): Screen {

  private lateinit var component: ScreenComponent

  private lateinit var world:    World
  private lateinit var renderer: Renderer
  private lateinit var stage:    MainStage

  init {
    component = componentFrom(gameComponent)
    component.inject(this)
  }

  @Inject
  fun inject(world: World, renderer: Renderer, stage: MainStage) {
    this.world    = world
    this.renderer = renderer
    this.stage    = stage
  }

  override fun render(delta: Float) {
    world.update(delta)
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

  //
  // Component
  private fun componentFrom(gameComponent: GameComponent): ScreenComponent {
    return DaggerScreenComponent.builder()
      .gameComponent(gameComponent)
      .screenModule(ScreenModule())
      .worldModule(WorldModule())
      .interfaceModule(InterfaceModule())
      .generationModule(GenerationModule())
      .build()
  }
}
