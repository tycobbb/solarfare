package dev.wizrad.solarfare.dagger.game

import dagger.Component
import dev.wizrad.solarfare.SolarFare
import javax.inject.Singleton

@Singleton @Component(
  modules = arrayOf(GameModule::class))
interface GameComponent: GameProvider {
  fun inject(app: SolarFare)
}
