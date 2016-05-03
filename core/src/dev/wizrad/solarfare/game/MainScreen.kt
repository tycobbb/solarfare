package dev.wizrad.solarfare.game

import com.badlogic.gdx.Screen
import dev.wizrad.solarfare.dagger.game.GameComponent
import dev.wizrad.solarfare.dagger.screen.DaggerScreenComponent
import dev.wizrad.solarfare.dagger.screen.GenerationModule
import dev.wizrad.solarfare.dagger.screen.ScreenComponent
import dev.wizrad.solarfare.dagger.screen.ScreenModule
import dev.wizrad.solarfare.game.minimap.Minimap
import dev.wizrad.solarfare.game.renderer.core.Renderer
import dev.wizrad.solarfare.game.world.World
import javax.inject.Inject

class MainScreen(
  gameComponent: GameComponent): Screen {

  private lateinit var component: ScreenComponent
  private lateinit var world:     World
  private lateinit var minimap:   Minimap
  private lateinit var renderer:  Renderer

  init {
    component = componentFrom(gameComponent)
    component.inject(this)
  }

  @Inject
  fun inject(world: World, minimap: Minimap, renderer: Renderer) {
    this.world    = world
    this.minimap  = minimap
    this.renderer = renderer
  }

  override fun render(delta: Float) {
    world.update(delta)
    minimap.update(delta)
    renderer.update(delta)
  }

  override fun resize(width: Int, height: Int) {
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
      .generationModule(GenerationModule())
      .build()
  }
}
