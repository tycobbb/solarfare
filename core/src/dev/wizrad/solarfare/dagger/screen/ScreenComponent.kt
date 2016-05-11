package dev.wizrad.solarfare.dagger.screen

import dagger.Component
import dev.wizrad.solarfare.dagger.game.GameComponent
import dev.wizrad.solarfare.game.MainScreen

@ScreenScope @Component(
  dependencies = arrayOf(GameComponent::class),
  modules      = arrayOf(ScreenModule::class, WorldModule::class, InterfaceModule::class, GenerationModule::class))
interface ScreenComponent {
  fun inject(screen: MainScreen)
}
